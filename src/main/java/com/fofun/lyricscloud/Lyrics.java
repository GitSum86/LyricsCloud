package com.fofun.lyricscloud;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lyrics {

	String path = "";
	
	// Set file path as a string
	public void setPath() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter filename: ");
		path = scanner.nextLine();

		// TO-DO: check for valid file
		
		// TO-DO: write contents of file into a string
		
		scanner.close();
	}

	// Returns the file path as a string
	public String getPath() {
		return path;
	}
	
	// TO-DO: create method to update lyrics
}
