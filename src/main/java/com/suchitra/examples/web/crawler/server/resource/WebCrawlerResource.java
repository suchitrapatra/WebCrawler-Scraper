package com.suchitra.examples.web.crawler.server.resource;


import java.util.Collections;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.suchitra.examples.web.crawler.CrawlerResponse;
import com.suchitra.examples.web.crawler.Scraper;
import com.suchitra.examples.web.crawler.SearchRecord;


@Path("")
public class WebCrawlerResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getSearchResults")
    public Response getSearchResults(@QueryParam("searchQuery") String searchQuery) {
        CrawlerResponse response = new CrawlerResponse();
        List<SearchRecord> searchRecords = Collections.emptyList();
        try {
            searchRecords = Scraper.search(searchQuery);
            response.getSearchRecords().clear();
            response.getSearchRecords().addAll(searchRecords);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generateOkResponse(response);
    }


    private static Response generateOkResponse(Object entity) {
        return Response.ok().entity(entity).build();
    }

}
