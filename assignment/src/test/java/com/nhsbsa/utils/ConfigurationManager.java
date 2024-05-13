package com.nhsbsa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationManager {
    private Properties prop = new Properties();

    private ConfigurationManager() {
        try (InputStream input = ConfigurationManager.class
                .getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input != null) {
                prop.load(input);
            } else {
                System.err.println("config.properties file not found");
            }
        } catch (IOException ex) {
            System.err.println("Error loading config.properties: " + ex.getMessage());
        }
    }

    private static class LazyHolder {
        private static final ConfigurationManager INSTANCE = new ConfigurationManager();
    }

    public static ConfigurationManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }

}
