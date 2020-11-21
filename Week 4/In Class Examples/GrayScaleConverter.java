import edu.duke.*;
import java.io.*;

public class GrayScaleConverter {
    public ImageResource makeGray (ImageResource inImage) {
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
            for (Pixel pixel: outImage.pixels()) {
                Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
                int avg = (inPixel.getRed() + inPixel.getBlue() + inPixel.getGreen()) / 3;
                pixel.setRed(avg);
                pixel.setGreen(avg);
                pixel.setBlue(avg);
        }
        return outImage;
    }

    public void testMakeGray () {
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }

    public void selectAndConvert() {
        DirectoryResource dr = new DirectoryResource();
        for (File f: dr.selectedFiles()) {
            ImageResource ir = new ImageResource(f);
            ImageResource gray = makeGray(ir);
            gray.draw();
        }
    }

    public static void main(String[] args) {
        GrayScaleConverter obj = new GrayScaleConverter();
        //obj.testMakeGray();
        obj.selectAndConvert();
    }
}

