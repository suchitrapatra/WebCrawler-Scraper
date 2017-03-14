package com.suchitra.examples.web.crawler;


import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class SearchRecord {

    private int index;
    private String title;
    private String price;


    public SearchRecord() {}


    public SearchRecord(int index, String title, String price) {
        super();
        this.index = index;
        this.title = title;
        this.price = price;
    }


    public int getIndex() {
        return index;
    }


    public void setIndex(int index) {
        this.index = index;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getPrice() {
        return price;
    }


    public void setPrice(String price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "SearchRecord [index=" + index + ", title=" + title + ", price=" + price + "]";
    }
}
