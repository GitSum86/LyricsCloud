package com.fofun.lyricscloud;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.net.URL;

public class Lyrics {

    private String filename;

    // Set file path as a string
    public void setPath(Scanner scanner) {
        System.out.print("Enter filename (in src/main/resources, e.g., test.txt): ");
        filename = scanner.nextLine().trim();  // Use trim() to remove any extra spaces

        // Check if the file exists in the resources
        if (!isValidFile(filename)) {
            System.out.println("Invalid file name or file does not exist. Please try again.");
            setPath(scanner); // Prompt again if invalid
        }
    }

    // Returns the absolute file path as a string
    public String getPath() {
        // Get the URL of the resource
        URL resource = getClass().getClassLoader().getResource(filename);
        if (resource == null) {
            throw new IllegalArgumentException("File not found: " + filename);
        }
        return resource.getPath();
    }

    // Read the contents of the file and return it as a String
    public String getLyricsText() {
        StringBuilder lyrics = new StringBuilder();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                throw new IOException("File not found: " + filename);
            }

            String line;
            while ((line = br.readLine()) != null) {
                lyrics.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
        }
        return lyrics.toString();
    }

    // Check if the file name is valid and exists in the resources
    private boolean isValidFile(String filename) {
        if (filename == null || filename.isEmpty()) {
            return false;
        }
        // Check if the resource exists
        URL resource = getClass().getClassLoader().getResource(filename);
        return resource != null;
    }
}