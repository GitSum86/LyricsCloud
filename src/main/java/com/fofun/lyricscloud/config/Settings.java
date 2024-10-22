package com.fofun.lyricscloud.config;

// Word Cloud Setup

public class Settings {
	
	// modify these WordCloud parameters as desired
	private static int dimensionX = 1000;
	private static int dimensionY = 1000;
	private static int radius = (((dimensionX/dimensionY)*dimensionX)/2);
	private static int padding = 6;
	private static int fontScalerMin = 50;
	private static int fontScalerMax = 200;


	public static int getDimensionX() {
		return dimensionX;
	}

	public static int getDimensionY() {
		return dimensionY;
	}

	public static int getRadius() {
		return radius;
	}
	
	public static int getPadding() {
		return padding;
	}
	
	public static int getFontScalerMin() {
		return fontScalerMin;
	}

	public static int getFontScalerMax() {
		return fontScalerMax;
	}

}
