package com.vatsal.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader: Loads test configuration from config.properties.
 */
public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_PATH = "src/test/resources/config.properties";

    static {
        try (FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties = new Properties();
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties: " + e.getMessage());
        }
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) throw new RuntimeException("Property '" + key + "' not found in config.properties");
        return value;
    }

    public static String getBrowser() {
        return get("browser");
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(get("headless"));
    }

    public static String getBaseUrl() {
        return get("base.url");
    }

    public static String getValidUsername() {
        return get("valid.username");
    }

    public static String getValidPassword() {
        return get("valid.password");
    }
}
