package com.suchitra.examples.web.crawler.db;


import static com.suchitra.examples.web.crawler.properties.ScraperProperties.getIntValue;
import static com.suchitra.examples.web.crawler.properties.ScraperProperties.getStringValue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class DBConnection {

    private static DBConnection instance = new DBConnection();
    ComboPooledDataSource dataSource;


    private DBConnection() {
        try {
            init();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static DBConnection getInstance() {
        return instance;
    }


    private void init() throws Exception {
        dataSource = new ComboPooledDataSource();
        String driverClass = getStringValue(DBConstants.DATABASE_DRIVER);
        Class.forName(driverClass).newInstance();
        dataSource.setDriverClass(driverClass);
        dataSource.setJdbcUrl(getStringValue(DBConstants.DATABASE_URL));
        dataSource.setUser(getStringValue(DBConstants.DATABASE_USER));
        dataSource.setPassword(getStringValue(DBConstants.DATABASE_PASSWORD));
        dataSource.setMinPoolSize(getIntValue(DBConstants.DATASOURCE_MIN_POOL_SIZE));
        dataSource.setMaxPoolSize(getIntValue(DBConstants.DATASOURCE_MAX_POOL_SIZE));
        dataSource.setAcquireIncrement(5);
    }


    public Connection getDBConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        connection.setAutoCommit(false);
        connection.setReadOnly(false);
        return connection;
    }


    public static void close(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void close(Connection connection) {
        if (connection == null) {
            return;
        }
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
