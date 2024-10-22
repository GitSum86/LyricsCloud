package com.fofun.lyricscloud;

import java.util.Arrays;
import java.util.List;

// Stop words to be ignored
public class StopWords {
    public static List<String> getStopWords() {
        return Arrays.asList(
            "the", "and", "a", "an", "in", "on", "at", "to", "of", "for",
            "with", "is", "are", "was", "were", "be", "been", "being",
            "have", "has", "had", "do", "does", "did", "but", "if",
            "or", "because", "as", "until", "while", "this", "that",
            "these", "those", "am", "i", "you", "he", "she", "it",
            "they", "we", "them", "his", "her", "its", "their", "our",
            "your", "yours", "my", "mine", "me", "him", "who", "whom",
            "which", "what", "where", "when", "why", "how", "it's", "i'm",
            "i'd"
        );
    }
}