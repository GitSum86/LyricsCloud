package com.fofun.lyricscloud;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Retrieves version number from pom.xml
public class Version {
    private static final String VERSION = loadVersion();

    // Load version number from version.properties
    private static String loadVersion() {
        Properties props = new Properties();
        try (InputStream input = Version.class.getClassLoader().getResourceAsStream("version.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find version.properties");
                return "Unknown";
            }
            props.load(input);
            return props.getProperty("app.version", "Unknown");
        } catch (IOException ex) {
            ex.printStackTrace();
            return "Unknown";
        }
    }

    // retrieve version number
    public static String getVersion() {
        return VERSION;
    }
}