import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * A simple single class Java program that takes any image and converts it to an ASCII text file.
 * @version 1.0
 * @author Joseph White
 * December 4th, 2017
 */
public class ImageToASCII {

    private double pixelValue;
    private static File imgInput;
    private static File imgOutput;
    private static String txtOutput;
    private BufferedImage originalImage;
    private FileWriter fileWriter;
    private PrintWriter printWriter;
    
    public ImageToASCII() {
        try {
            printWriter = new PrintWriter(fileWriter = new FileWriter(imgOutput, true));
        } 
        catch (IOException e) {
        }
    }
    
    /**
     * Takes the imported image, denotes every pixel to numerical value based on their RBG color, and initiates the printing function for each pixel value.
     * @param image
     */
    public void convertToAscii(File image) {
        try {
        	originalImage = ImageIO.read(image);
        } 
        catch (IOException e) {
        	JOptionPane.showMessageDialog(null, "Error Occured! File does not exist. Conversion failed!");
        }
        
        for (int i = 0; i < originalImage.getHeight(); i++) {
            for (int j = 0; j < originalImage.getWidth(); j++) {
                Color pixelColor = new Color(originalImage.getRGB(j, i));
                pixelValue = (((pixelColor.getRed() * 0.30) + (pixelColor.getBlue() * 0.59) + (pixelColor.getGreen() * 0.11)));
                print(strChar(pixelValue));
                print(strChar(pixelValue));
            }
            try {
                printWriter.println("");
                printWriter.flush();
                fileWriter.flush();
                }
            catch (Exception ex) {
            }
        }
    }
    /**
     * 
     * @param pixel The color value of the pixel.
     * @see convertToAscii(File image)
     * @return str
     */
    public String strChar(double pixel) {
        String str = " ";
        if (pixel >= 240) {
            str = " ";
        }
        else if (pixel >= 210) {
            str = ".";
        }
        else if (pixel >= 190) {
            str = "*";
        }
        else if (pixel >= 170) {
            str = "+";
        }
        else if (pixel >= 120) {
            str = "^";
        }
        else if (pixel >= 110) {
            str = "&";
        }
        else if (pixel >= 80) {
            str = "8";
        }
        else if (pixel >= 60) {
            str = "#";
        }
        else {
        	str = "@";
        }
        return str;
    }

    public void print(String str) {
        try {
            printWriter.print(str);
            printWriter.flush();
            fileWriter.flush();
        }
        catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
    	
    	JOptionPane.showMessageDialog(null, "Please select an image file you would like to convert.");
    	
    	JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & GIF Images", "jpg", "gif", "png");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	imgInput = chooser.getSelectedFile();
        	//String imgPath = imgInput.getAbsolutePath();
        	System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());
        }
        
        txtOutput = "temp.txt";
        JOptionPane.showMessageDialog(null, "Thank you. Now please type out the name of the txt file you want the converted image stored into. Please be sure include '.txt' at the end of the name.");
        
        filter = new FileNameExtensionFilter("TXT Files", "txt");
        chooser.setFileFilter(filter);
        returnVal = chooser.showOpenDialog(null);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
        	imgOutput = chooser.getSelectedFile();
        	System.out.println("You chose to save to this file: " + chooser.getSelectedFile().getName());
        }

        ImageToASCII obj = new ImageToASCII();
        obj.convertToAscii(imgInput);
        JOptionPane.showMessageDialog(null, "Thank you. your converted text file can be found in the project folder.");
    }
}