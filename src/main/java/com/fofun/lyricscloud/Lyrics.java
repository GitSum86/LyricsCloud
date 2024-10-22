package com.fofun.lyricscloud;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Scanner;
import java.net.URL;

// Manages the lyrics file
public class Lyrics {

    private String filename;

    // Set file path as a string
    public void setPath(Scanner scanner) {
        System.out.print("Enter filename (in src/main/resources, e.g., test.txt): ");
        filename = scanner.nextLine().trim();  // Use trim() to remove any extra spaces
        
        if (!isValidFile(filename)) {
	        	System.out.println();
	            System.out.println("Invalid file name or file does not exist. Please try again.");
	            System.out.println();
	            setPath(scanner); // Prompt again if invalid
        }
    }

    // Returns the filename as a string
    public String getFile() {
    	return filename;
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

 // Read the contents of the file and return it as a String with preserved blank lines
    public String getLyricsText() {
        StringBuilder lyrics = new StringBuilder();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {

            if (inputStream == null) {
                throw new IOException("File not found: " + filename);
            }

            String line;
            boolean previousLineWasBlank = false;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    if (!previousLineWasBlank) {
                        // Append an extra newline to indicate a paragraph break
                        lyrics.append("\n\n");
                        previousLineWasBlank = true;
                    }
                    // Else, skip adding another newline to avoid multiple blank lines
                } else {
                    lyrics.append(line).append("\n");
                    previousLineWasBlank = false;
                }
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