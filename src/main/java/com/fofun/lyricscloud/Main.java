package com.fofun.lyricscloud;

import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.WordFrequency;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Lyrics Cloud - By Peter Wang and Elijah");

        // Initialize the Scanner in a try-with-resources to ensure it is closed properly
        try (Scanner scanner = new Scanner(System.in)) {
            Lyrics lyric = new Lyrics();
            lyric.setPath(scanner);

            System.out.println("Loading " + lyric.getPath());

            FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();

            // Set stop words
            frequencyAnalyzer.setStopWords(StopWords.getStopWords());

            // Optionally, set minimum and maximum word lengths
            frequencyAnalyzer.setMinWordLength(3); // Ignore words shorter than 3 characters
            frequencyAnalyzer.setMaxWordLength(15); // Ignore words longer than 15 characters

            try {
                // Use the file path approach
                File lyricsFile = new File(lyric.getPath());
                List<WordFrequency> frequencies = frequencyAnalyzer.load(lyricsFile);

                // Print the frequencies
                for (WordFrequency word : frequencies) {
                    System.out.println(word.getWord() + ": " + word.getFrequency());
                }
            } catch (Exception e) {
                System.out.println("Exception - An error occurred while analyzing the lyrics. " + e.getMessage());
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error - " + e.getMessage());
        }
    }
}