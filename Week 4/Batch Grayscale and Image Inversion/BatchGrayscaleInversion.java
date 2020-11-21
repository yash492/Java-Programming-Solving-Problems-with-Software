import edu.duke.*;
import java.io.*;

public class BatchGrayscaleInversion {
    public ImageResource makeGray (ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int avg = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setRed(avg);
            pixel.setGreen(avg);
            pixel.setBlue(avg);
        }
        return outImage;
    }

    public ImageResource makeInverse (ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        for (Pixel pixel: outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int redInverse = 255 - inPixel.getRed();
            int greenInverse = 255 - inPixel.getGreen();
            int blueInverse = 255 - inPixel.getBlue();
            pixel.setRed(redInverse);
            pixel.setGreen(greenInverse);
            pixel.setBlue(blueInverse);
        }
        return outImage;
    }

    public void imageSaver(String type) {
        DirectoryResource dr = new DirectoryResource();
        ImageResource outputImage = null;
        for(File f: dr.selectedFiles()) {
            ImageResource inputImage = new ImageResource(f);
            if (type.equals("inverse")) {
                outputImage = makeInverse(inputImage);
            }

            else {
                outputImage = makeGray(inputImage);
            }

            String newName = type + "-" + inputImage.getFileName();
            outputImage.setFileName(newName);
            outputImage.save();
            outputImage.draw();
        }
    }

    public static void main (String[] args) {
        BatchGrayscaleInversion obj = new BatchGrayscaleInversion();
        obj.imageSaver("gray");
    }
    
} 