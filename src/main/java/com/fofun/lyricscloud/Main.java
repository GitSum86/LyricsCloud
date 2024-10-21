package com.fofun.lyricscloud;
import com.kennycason.kumo.*;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Lyrics Cloud - By Peter Wang and Elijah ");
		
		Lyrics lyric = new Lyrics();
		
		lyric.setPath();
		
		System.out.println("Loading " + lyric.getPath());

	}
	
	//  TO-DO Integrate with Kumo (https://github.com/kennycason/kumo.git)

}
