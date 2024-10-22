package com.fofun.lyricscloud;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MaskLoader {

    // Loads mask from file
    public BufferedImage loadMask(File maskFile) throws IOException {
        if (!maskFile.exists()) {
            throw new IOException("Mask file not found at: " + maskFile.getPath());
        }
        BufferedImage maskImage = ImageIO.read(maskFile);
        if (maskImage == null) {
            throw new IOException("Failed to load mask image from: " + maskFile.getPath());
        }
        return maskImage;
    }
}