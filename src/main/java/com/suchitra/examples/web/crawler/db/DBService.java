package com.suchitra.examples.web.crawler.db;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.suchitra.examples.web.crawler.SearchRecord;


public class DBService {

    private static DBService instance = new DBService();

    private DBConnection dbConnection = DBConnection.getInstance();


    private DBService() {}


    public static DBService getInstance() {
        return instance;
    }


    public void insertSearchRecords(List<SearchRecord> searchRecords) throws Exception {

        Connection connection = null;
        PreparedStatement insertStmt = null;
        try {
            connection = dbConnection.getDBConnection();
            final String insertQuery = DBStatements.INSERT_SEARCH_RECORD;
            insertStmt = connection.prepareStatement(insertQuery);
            for (SearchRecord searchRecord : searchRecords) {
                insertStmt.setInt(1, searchRecord.getIndex());
                insertStmt.setString(2, searchRecord.getTitle());
                insertStmt.setString(3, searchRecord.getPrice());
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
            connection.commit();
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            DBConnection.close(insertStmt);
            DBConnection.close(connection);
        }
    }


    public List<SearchRecord> getSearchRecords(String searchQuery) throws Exception {

        Connection connection = null;
        PreparedStatement selectStmt = null;
        ResultSet resultSet = null;
        List<SearchRecord> searchRecords = new ArrayList<SearchRecord>();
        try {
            connection = dbConnection.getDBConnection();
            final String selectQuery = DBStatements.SELECT_SEARCH_RECORD;
            selectStmt = connection.prepareStatement(selectQuery);
            // selectStmt.setString(1, searchQuery);
            selectStmt.setString(1, "%" + searchQuery + "%");
            resultSet = selectStmt.executeQuery();
            while (resultSet.next()) {
                int index = resultSet.getInt(DBColumns.FIELD_RECORD_INDEX);
                String title = resultSet.getString(DBColumns.FIELD_RECORD_TITLE);
                String price = resultSet.getString(DBColumns.FIELD_RECORD_PRICE);
                SearchRecord searchRecord = new SearchRecord(index, title, price);
                searchRecords.add(searchRecord);
            }
        } catch (SQLException e) {
            throw new Exception(e);
        } finally {
            DBConnection.close(resultSet);
            DBConnection.close(selectStmt);
            DBConnection.close(connection);
        }
        return searchRecords;
    }

}
