package com.suchitra.examples.web.crawler;


import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.suchitra.examples.web.crawler.db.DBService;


public class Scraper {

    private static final DBService dbService = DBService.getInstance();


    public static void main(String[] args) throws Exception {
        int index = 1;
        List<SearchRecord> totalSearchRecords = new LinkedList<SearchRecord>();
        String urlHref = "https://newyork.craigslist.org/search/bka";
        do {
            List<SearchRecord> searchRecords = new LinkedList<SearchRecord>();
            Document document = Jsoup.connect(urlHref).get();
            System.out.println(urlHref);
            for (Element row : document.select("ul.rows li.result-row p")) {
                String title = row.select(".result-title").text();
                String price = row.select(".result-price").text();
                SearchRecord searchRecord = new SearchRecord(index, title, price);
                searchRecords.add(searchRecord);
                System.out.println(searchRecord);
                index++;
            }
            Element next = document.select("span.buttons a.button.next").first();
            urlHref = next.attr("abs:href");
            dbService.insertSearchRecords(searchRecords);
            totalSearchRecords.addAll(searchRecords);
        } while (index <= 1000);
        System.out.println("Search records size = " + totalSearchRecords.size());
    }


    public static List<SearchRecord> search(String searchQuery) throws Exception {
        return dbService.getSearchRecords(searchQuery);
    }

}
