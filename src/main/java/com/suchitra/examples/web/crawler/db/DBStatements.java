package com.suchitra.examples.web.crawler.db;


public interface DBStatements {

    public static final String INSERT_SEARCH_RECORD =
        "insert into search_records (record_index, record_title, record_price) values (?, ?, ?)";

    public static final String SELECT_SEARCH_RECORD =
        "select record_index, record_title, record_price from search_records where record_title like ?";

}
