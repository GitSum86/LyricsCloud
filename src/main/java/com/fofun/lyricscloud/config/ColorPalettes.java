package com.fofun.lyricscloud.config;

import com.kennycason.kumo.palette.ColorPalette;
import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

// A utility class that defines multiple gradient color palettes for WordCloud generation.
public class ColorPalettes {

    // Define individual color palettes
    public static final ColorPalette SUNSET_PALETTE = new ColorPalette(
        new Color(0xFF5733), // Red
        new Color(0xFF8D1A), // Orange
        new Color(0xFFC300), // Yellow
        new Color(0xFFDA33), // Light Yellow
        new Color(0xFFE066), // Pale Yellow
        new Color(0xFFFFFF)  // White
    );

    public static final ColorPalette OCEAN_PALETTE = new ColorPalette(
        new Color(0x004E7C), // Dark Blue
        new Color(0x007ACC), // Blue
        new Color(0x00A3E0), // Light Blue
        new Color(0x00C1E1), // Sky Blue
        new Color(0x00E5D4), // Teal
        new Color(0xFFFFFF)  // White
    );

    public static final ColorPalette FOREST_PALETTE = new ColorPalette(
        new Color(0x013220), // Dark Green
        new Color(0x026440), // Forest Green
        new Color(0x039660), // Medium Green
        new Color(0x04B880), // Lime Green
        new Color(0x05D9A0), // Light Green
        new Color(0xFFFFFF)  // White
    );

    public static final ColorPalette VIOLET_PALETTE = new ColorPalette(
        new Color(0x4B0082), // Indigo
        new Color(0x6A0DAD), // Purple
        new Color(0x8A2BE2), // Blue Violet
        new Color(0x9B30FF), // Purple Heart
        new Color(0xBA55D3), // Medium Orchid
        new Color(0xFFFFFF)  // White
    );

    public static final ColorPalette PASTEL_PALETTE = new ColorPalette(
        new Color(0xFFB3BA), // Light Pink
        new Color(0xFFDFBA), // Peach
        new Color(0xFFFFBA), // Light Yellow
        new Color(0xBAFFC9), // Mint Green
        new Color(0xBAE1FF), // Light Blue
        new Color(0xFFFFFF)  // White
    );

    public static final ColorPalette MONOCHROME_BLUE_PALETTE = new ColorPalette(
        new Color(0x0000FF), // Blue
        new Color(0x3333FF), // Medium Blue
        new Color(0x6666FF), // Light Blue
        new Color(0x9999FF), // Very Light Blue
        new Color(0xCCCCFF), // Pastel Blue
        new Color(0xFFFFFF)  // White
    );

    public static final ColorPalette RAINBOW_PALETTE = new ColorPalette(
        new Color(0xFF0000), // Red
        new Color(0xFF7F00), // Orange
        new Color(0xFFFF00), // Yellow
        new Color(0x00FF00), // Green
        new Color(0x0000FF), // Blue
        new Color(0x8B00FF)  // Violet
    );

    // Aggregate all palettes into a list for easy access
    private static final List<ColorPalette> COLOR_PALETTES = Arrays.asList(
        SUNSET_PALETTE,
        OCEAN_PALETTE,
        FOREST_PALETTE,
        VIOLET_PALETTE,
        PASTEL_PALETTE,
        MONOCHROME_BLUE_PALETTE,
        RAINBOW_PALETTE
    );

    private static final Random RANDOM = new Random();

    // Return a random ColorPalette from the COLOR_PALETTES List
    public static ColorPalette getRandomColorPalette() {
        int index = RANDOM.nextInt(COLOR_PALETTES.size());
        ColorPalette selectedPalette = COLOR_PALETTES.get(index);
        System.out.println("Selected Color Palette: " + getPaletteName(selectedPalette));
        return selectedPalette;
    }

    // Return the palette name
    private static String getPaletteName(ColorPalette palette) {
        if (palette.equals(SUNSET_PALETTE)) {
            return "Sunset Palette";
        } else if (palette.equals(OCEAN_PALETTE)) {
            return "Ocean Palette";
        } else if (palette.equals(FOREST_PALETTE)) {
            return "Forest Palette";
        } else if (palette.equals(VIOLET_PALETTE)) {
            return "Violet Palette";
        } else if (palette.equals(PASTEL_PALETTE)) {
            return "Pastel Palette";
        } else if (palette.equals(MONOCHROME_BLUE_PALETTE)) {
            return "Monochrome Blue Palette";
        } else if (palette.equals(RAINBOW_PALETTE)) {
            return "Rainbow Palette";
        } else {
            return "Unknown Palette";
        }
    }
}