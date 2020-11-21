import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CSVMax {
    public CSVRecord hottestHourInFile(CSVParser parser) {
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow: parser) {
            largestSoFar = getLargestOfTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public CSVRecord hottestInManyDays() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord largestSoFar = null;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVRecord currentFile = hottestHourInFile(fr.getCSVParser());
            largestSoFar = getLargestOfTwo(currentFile, largestSoFar);
            }
            return largestSoFar;
        }
    
    public CSVRecord getLargestOfTwo(CSVRecord currentRow, CSVRecord largestSoFar) {
        if(largestSoFar == null) {
            largestSoFar = currentRow;
        }
        else {
            double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            if (largestTemp < currentTemp) {
                largestSoFar = currentRow;
            }
        }
        return largestSoFar;
    }

    public void testHottestInDay() {
        FileResource fr = new FileResource();
        CSVRecord largest = hottestHourInFile(fr.getCSVParser());
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("TimeEST") + ".");
    }

    public void testhottestInManyDays() {
        CSVRecord largest = hottestInManyDays();
        System.out.println("Hottest temperature was " + largest.get("TemperatureF") + " at " + largest.get("DateUTC"));
    }

    public static void main(String[] args) {
        CSVMax obj = new CSVMax();
        //obj.testHottestInDay();
        obj.testhottestInManyDays();
    }
}