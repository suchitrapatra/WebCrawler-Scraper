package com.suchitra.examples.web.crawler.properties;


import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


public class ScraperProperties {

    private static final Map<String, String> propertyMap = new HashMap<String, String>();

    static {
        ScraperProperties.load();
    }


    public static void load() {

        Properties properties = getPropertyFromClasspath("scraper.properties");
        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            String key = (String) entry.getKey();
            String value = (String) entry.getValue();
            propertyMap.put(key, value);
        }
    }


    private static Properties getPropertyFromClasspath(String filepath) {
        Properties properties = new Properties();

        try {
            properties = getPropertiesFromClasspath(filepath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (null == properties) {
            throw new RuntimeException("Property file not loaded :: " + filepath);
        }

        return properties;
    }


    private static Properties getPropertiesFromClasspath(String pathRelativeToClasspath) {
        Properties properties = new Properties();

        try {
            InputStream is = ScraperProperties.class.getResourceAsStream(pathRelativeToClasspath);
            if (is == null) {
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathRelativeToClasspath);
            }
            if (is != null) {
                properties.load(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }


    public static String getStringValue(String key) {
        return propertyMap.get(key);
    }


    public static int getIntValue(String key) {
        return Integer.parseInt(propertyMap.get(key));
    }

}
