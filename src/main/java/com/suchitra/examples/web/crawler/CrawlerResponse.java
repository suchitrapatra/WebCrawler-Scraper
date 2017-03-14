package com.suchitra.examples.web.crawler;


import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class CrawlerResponse {

    @XmlElement
    private List<SearchRecord> searchRecords;


    public List<SearchRecord> getSearchRecords() {
        if (searchRecords == null) {
            searchRecords = new ArrayList<SearchRecord>();
        }
        return searchRecords;
    }

}
