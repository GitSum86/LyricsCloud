package com.fofun.lyricscloud;
import com.fofun.lyricscloud.config.ColorPalettes;
import com.fofun.lyricscloud.config.Settings;

import com.kennycason.kumo.WordCloud;
import com.kennycason.kumo.WordFrequency;
import com.kennycason.kumo.bg.CircleBackground;
import com.kennycason.kumo.bg.PixelBoundaryBackground;
import com.kennycason.kumo.bg.RectangleBackground;
import com.kennycason.kumo.font.KumoFont;
import com.kennycason.kumo.font.scale.LinearFontScalar;
import com.kennycason.kumo.nlp.FrequencyAnalyzer;
import com.kennycason.kumo.palette.ColorPalette;
import com.kennycason.kumo.CollisionMode;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;


public class Main extends Application {

    private Lyrics lyric = new Lyrics();
    private FrequencyAnalyzer frequencyAnalyzer = new FrequencyAnalyzer();
    private List<WordFrequency> frequencies;
    private boolean filterStopWords = true;
    private String nextStateStopWords = "ON";
    private Scanner scanner;

    public static void main(String[] args) {
        launch(args); // Start the JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        // Start your console application logic in a new thread
        new Thread(this::run).start();
    }

    // Main application logic
    public void run() {
        scanner = new Scanner(System.in);
        try (Scanner scanner = new Scanner(System.in)) {
            boolean exit = false;
            while (!exit) {
                displayMenu();
                System.out.print("Enter your choice: ");
                String input = scanner.nextLine().trim();
                System.out.println();

                switch (input) {
                    case "1":
                    	lyric.setPath(scanner);
                        loadLyrics();
                        break;
                    case "2":
                    	if (filterStopWords == true) {
                    		filterStopWords = false;
                    		nextStateStopWords = "OFF";
                    		System.out.println("Stop words WILL NOT be filtered from the analysis");
                    		loadLyrics();
                    	} else {
                    		filterStopWords = true;
                    		nextStateStopWords = "ON";
                    		System.out.println("Stop words WILL be filtered from the analysis");
                    		loadLyrics();
                    	}
                    	break;
                    case "3":
                        showWordFrequencies();
                        break;
                    case "4":
                        generateWordCloud();
                        break;
                    case "5":
                        System.out.println("Exiting the application. Goodbye!");
                        exit = true;

                        // Exit JavaFX and terminate JVM
                        Platform.exit();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
                System.out.println();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error - " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error - " + e.getMessage());
        }
    }

    // Display the menu to the user
    public void displayMenu() {

    	System.out.println("Lyrics Cloud v" + Version.getVersion());
        System.out.println();
        System.out.println("[Main Menu]");
        System.out.println("[1] Load Lyrics (" + lyric.getFile() + ")");
        System.out.println("[2] Filter Stop Words (Currently: " + nextStateStopWords + ")");
        System.out.println("[3] Show Word Frequency Analysis");
        System.out.println("[4] Generate Word Cloud");
        System.out.println("[5] Exit");
    }

    // Load lyrics based on user input
    private void loadLyrics() {


        System.out.println();
        System.out.println("Loading " + lyric.getPath());
        System.out.println();

        // Configure FrequencyAnalyzer
        if (filterStopWords == true) {
            frequencyAnalyzer.setStopWords(StopWords.getStopWords());
        }
        frequencyAnalyzer.setMinWordLength(2); // Ignore words shorter than 3 characters
        frequencyAnalyzer.setMaxWordLength(15); // Ignore words longer than 15 characters

        try {
            // Use the file path approach
            File lyricsFile = new File(lyric.getPath());
            frequencies = frequencyAnalyzer.load(lyricsFile);
            System.out.println("Lyrics loaded and analyzed successfully.");
        } catch (Exception e) {
            System.out.println("Exception - An error occurred while analyzing the lyrics. " + e.getMessage());
        }
    }

    // Display word frequencies to the user
    private void showWordFrequencies() {
        if (frequencies == null) {
            System.out.println("No lyrics loaded. Please load lyrics first (Option 1).");
            return;
        }

        System.out.println("[Word Frequency Analysis Results]");
        for (WordFrequency word : frequencies) {
            System.out.println(word.getWord() + ": " + word.getFrequency());
        }
        System.out.println();

        boolean exit = false; // initialize prompt state flag for input validation

        // Ask if the user wants to visualize as a bar chart
        System.out.print("Would you like to view a bar chart for the word frequencies? (y/n): ");
        System.out.println();
        while (!exit) {
            String chartInput = scanner.nextLine().trim().toLowerCase();
            if (chartInput.equals("y")) {
                showBarChart(); // Displaying the bar chart
                break;
            }
            if (chartInput.equals("n")) {
                exit = true;
            } else {
            System.out.print("Invalid input. Please enter 'y' or 'n': ");
            }
        }

        exit = false; // Reset prompt state flag

        // Ask if the user wants to export to Excel
        System.out.print("Would you like to export the word frequencies to an Excel spreadsheet? (y/n): ");
        System.out.println();
        while (!exit) {
            String exportInput = scanner.nextLine().trim().toLowerCase();
            if (exportInput.equals("y")) {
                System.out.print("Enter the desired filename (without extension): ");
                String filename = scanner.nextLine().trim();
                exportToExcel(filename + ".xlsx");
                break;
            }
            if (exportInput.equals("n")) {
                exit = true;
            } else {
            System.out.print("Invalid input. Please enter 'y' or 'n': ");
            }
        }
    }

    // Show a bar chart of word frequencies using JavaFX
    private void showBarChart() {
        if (frequencies == null || frequencies.isEmpty()) {
            System.out.println("No lyrics loaded. Please load lyrics first (Option 1).");
            return;
        }

        // Use Platform.runLater to ensure the code runs on the JavaFX Application Thread
        Platform.runLater(() -> {
            Stage stage = new Stage();
            stage.setTitle("Word Frequency Bar Chart");

            CategoryAxis xAxis = new CategoryAxis();
            xAxis.setLabel("Words");

            NumberAxis yAxis = new NumberAxis();
            yAxis.setLabel("Frequency");

            BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
            barChart.setTitle("Word Frequency Analysis");

            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Word Frequencies");

            // Adding the word frequencies to the chart
            int maxWords = 20; // Limit the number of words displayed
            int count = 0;
            for (WordFrequency word : frequencies) {
                if (count >= maxWords) break;
                series.getData().add(new XYChart.Data<>(word.getWord(), word.getFrequency()));
                count++;
            }

            barChart.getData().add(series);

            Scene scene = new Scene(barChart, 800, 600);
            stage.setScene(scene);
            stage.show();
        });
    }

    // Export word frequencies to Excel
    private void exportToExcel(String filename) {
        if (frequencies == null || frequencies.isEmpty()) {
            System.out.println("No lyrics loaded. Please load lyrics first (Option 1).");
            return;
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Word Frequencies");

        // Create header row
        Row headerRow = sheet.createRow(0);
        Cell headerCell1 = headerRow.createCell(0);
        headerCell1.setCellValue("Word");
        Cell headerCell2 = headerRow.createCell(1);
        headerCell2.setCellValue("Frequency");

        // Populate the rows with word frequency data
        int rowNum = 1;
        for (WordFrequency word : frequencies) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(word.getWord());
            row.createCell(1).setCellValue(word.getFrequency());
        }

        // Write the output to the specified file
        try (FileOutputStream fileOut = new FileOutputStream("src/main/output/" + filename)) {
            workbook.write(fileOut);
            System.out.println("Word frequencies successfully exported to " + filename);
        } catch (IOException e) {
            System.out.println("Error writing to Excel file: " + e.getMessage());
        } finally {
            try {
                workbook.close();
            } catch (IOException e) {
                System.out.println("Error closing workbook: " + e.getMessage());
            }
        }
    }

    // Generate a word cloud with a random background shape and save it to "src/main/output/"
    private void generateWordCloud() {

    	// Check whether lyrics are loaded, if not, return to main menu
        if (frequencies == null || frequencies.isEmpty()) {
            System.out.println("No lyrics loaded. Please load lyrics first using Option [1].");
            return;
        }

        // Define output directory and ensure it exists
        String outputDirPath = "src/main/output/";
        File outputDir = new File(outputDirPath);
        if (!outputDir.exists()) {
            boolean dirsCreated = outputDir.mkdirs();
            if (!dirsCreated) {
                System.out.println("Failed to create output directory: " + outputDirPath);
                return;
            }
        }

        // Define the output file path
        String outputFilePath = outputDirPath + "wordcloud.png";

        // Randomly select a background shape
        com.kennycason.kumo.bg.Background chosenBackground;
        Random random = new Random();
        int choice = random.nextInt(3);

        // Possible Random Background Shapes
        switch (choice) {
            case 0:
                // Rendering CircleBackground
                chosenBackground = new CircleBackground(Settings.getRadius());
                System.out.println("Selected background: CircleBackground");
                break;
            case 1:
                // Rendering RectangleBackground
                chosenBackground = new RectangleBackground(new Dimension(Settings.getDimensionX(), Settings.getDimensionY()));
                System.out.println("Selected background: RectangleBackground");
                break;
            case 2:
                // PixelBoundryBackground using a random mask from backgrounds directory
                chosenBackground = getRandomPixelBoundaryBackground();
                if (chosenBackground != null) {
                    System.out.println("Selected background: PixelBoundaryBackground with mask.");
                } else {
                    // Fallback to CircleBackground if no mask is found or error occurs
                    chosenBackground = new CircleBackground(Settings.getRadius());
                    System.out.println("PixelBoundaryBackground selection failed. Defaulted to CircleBackground.");
                }
                break;
            default:
                chosenBackground = new CircleBackground(Settings.getRadius());
                System.out.println("Defaulted to background: CircleBackground");
        }

        // Possible Fonts
        final Font[] FONTS = new Font[] {
                new Font("Lucida Sans", Font.PLAIN, Settings.getFontScalerMin()),
                new Font("Comic Sans", Font.PLAIN, Settings.getFontScalerMin()),
                new Font("Yu Gothic Light", Font.PLAIN, Settings.getFontScalerMin()),
                new Font("Meiryo", Font.PLAIN, Settings.getFontScalerMin())
        };

        // Configure the WordCloud
        Dimension dimension = new Dimension(Settings.getDimensionX(), Settings.getDimensionY());
        WordCloud wordCloud = new WordCloud(dimension, CollisionMode.PIXEL_PERFECT);
        wordCloud.setPadding(Settings.getPadding());
        wordCloud.setBackground(chosenBackground);

        // Set a randomly selected color palette from ColorPalettes
        ColorPalette selectedPalette = ColorPalettes.getRandomColorPalette();
        wordCloud.setColorPalette(selectedPalette);

        // Set Font Scale
        wordCloud.setFontScalar(new LinearFontScalar(Settings.getFontScalerMin(), Settings.getFontScalerMax()));

        // Set Font Face
        Random rand = new Random();
        int randomNum = rand.nextInt(4);
        wordCloud.setKumoFont(new KumoFont(FONTS[randomNum]));

        try {
            // Build and write the word cloud
            wordCloud.build(frequencies);
            wordCloud.writeToFile(outputFilePath);
            System.out.println("Word cloud generated successfully at " + outputFilePath);
        } catch (Exception e) {
            System.out.println("Error generating word cloud: " + e.getMessage());
        }
    }


    // Selects random image for PixelBoundaryBackground and resizes the mask according to the WordCloud Settings
    private PixelBoundaryBackground getRandomPixelBoundaryBackground() {
        try {
            // Get the backgrounds directory from resources
            URL backgroundsURL = getClass().getClassLoader().getResource("backgrounds/");
            if (backgroundsURL == null) {
                System.out.println("Backgrounds directory not found in resources.");
                return null;
            }

            // Convert URL to URI and then to File
            File backgroundsDir = new File(backgroundsURL.toURI());

            if (!backgroundsDir.exists() || !backgroundsDir.isDirectory()) {
                System.out.println("Backgrounds directory is invalid.");
                return null;
            }

            // List all image files
            File[] maskFiles = backgroundsDir.listFiles((dir, name) ->
	            name.toLowerCase().endsWith(".png") ||
	            name.toLowerCase().endsWith(".bmp") ||
	            name.toLowerCase().endsWith(".jpg") ||
	            name.toLowerCase().endsWith(".jpeg"));

            // No images found in backgrounds directory
            if (maskFiles == null || maskFiles.length == 0) {
                System.out.println("No mask images found in backgrounds directory.");
                return null;
            }

            // Randomly select a mask file
            Random random = new Random();
            File selectedMask = maskFiles[random.nextInt(maskFiles.length)];

            // Resize the selected mask file to the dimensions of the Word Cloud (maintains aspect ratio)
            MaskLoader loader = new MaskLoader();
            ImageResizer resizer = new ImageResizer();
            try {
                BufferedImage originalMask = loader.loadMask(maskFiles[random.nextInt(maskFiles.length)]);
                int targetWidth = Settings.getDimensionX();
                int targetHeight = Settings.getDimensionY();
                BufferedImage resizedMask = resizer.resizeImageMaintainingAspectRatio(originalMask, targetWidth, targetHeight);
                ImageIO.write(resizedMask, "png", new File("src/main/resources/resized_mask.png"));
            } catch (IOException e) {
                System.err.println("Error processing mask: " + e.getMessage());
            }

            // Debug to announce mask filename used
            System.out.println("Using mask file: " + selectedMask.getName());

            // Create PixelBoundaryBackground otherwise announce exceptions
            return new PixelBoundaryBackground("src/main/resources/resized_mask.png");
	        } catch (URISyntaxException e) {
	            System.out.println("URISyntaxException while accessing backgrounds: " + e.getMessage());
	        } catch (IOException e) {
	            System.out.println("IOException while reading mask image: " + e.getMessage());
	        } catch (Exception e) {
	            System.out.println("Exception while creating PixelBoundryBackground: " + e.getMessage());
	        }
	        return null;
    }

}