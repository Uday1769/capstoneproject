package com.demo.base;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class ConfigReader {
    private static Properties prop;

    static {
        try {
            FileInputStream file = new FileInputStream("src/test/resources/config.properties");
            prop = new Properties();
            prop.load(file);
            file.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties");
        }
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String getURL(String key) {
        return prop.getProperty(key);
    }
}
