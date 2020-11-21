import java.io.*;
import org.apache.commons.csv.*;
import edu.duke.*;

public class ParsingWeatherData {
    //Gets the smallest temperture in the file.
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord smallestSoFar = null;
        for (CSVRecord row: parser) {
            smallestSoFar = smallestOfTwo(smallestSoFar, row, "TemperatureF");
        }
        return smallestSoFar;
    }

    public String fileWithColdestTemperature() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord smallestSoFar = null;
        File smallestFileSoFar = null;
        for(File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentFile = coldestHourInFile(parser);
            if (smallestSoFar == null) {
                smallestSoFar = currentFile;
            }
            if (smallestFileSoFar == null) {
                smallestFileSoFar = f;
            }
            else {
                double currentTemp = Double.parseDouble(currentFile.get("TemperatureF"));
                double smallestTemp = Double.parseDouble(smallestSoFar.get("TemperatureF"));
                if (currentTemp < smallestTemp && currentTemp != -9999) {
                    smallestFileSoFar = f;
                    smallestSoFar = currentFile;
                 }
            }
        }
        return smallestFileSoFar.getPath();
    }

    public CSVRecord lowestHumidityInFile(CSVParser parser){
        CSVRecord smallestSoFar = null;
        for (CSVRecord row: parser) {
            smallestSoFar = smallestOfTwo(smallestSoFar, row, "Humidity");
        }
        return smallestSoFar;
    }

    public String getFileName (String filePath) {
        int indexFile = filePath.lastIndexOf("weather");
        return filePath.substring(indexFile);
    }

    //Returns smallest of two values. 
    public CSVRecord smallestOfTwo(CSVRecord smallestSoFar, CSVRecord row, String columnName) {
        if (smallestSoFar == null) {
            smallestSoFar = row;
        }
        else {
            if (row.get(columnName).equals("N/A")) {
                return null;
            }
            double smallestTemp = Double.parseDouble(smallestSoFar.get(columnName));
            double currentTemp = Double.parseDouble(row.get(columnName));
            if (currentTemp == -9999) {
                return null;
            }
            if (currentTemp < smallestTemp) {
                smallestSoFar = row;
            }   
        }
        return smallestSoFar;
    }  
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr =  new DirectoryResource();
        CSVRecord smallestSoFar = null;
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentHumidity = lowestHumidityInFile(parser);
            smallestSoFar = smallestOfTwo(smallestSoFar, currentHumidity, "Humidity");
        }
        return smallestSoFar;
    }

    public void averageTemperatureInFile(CSVParser parser) {
        double sum = 0;
        int count = 0;
        for (CSVRecord record: parser) {
            count = count + 1;
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            sum = currentTemp + sum;
        }
        double avg = sum/ (double) count;
        System.out.println("Average temperature in file is " + avg);
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        int count = 0;
        double sum = 0;
        double avg = 0;
        for (CSVRecord record: parser) {
            double currentTemp = Double.parseDouble(record.get("TemperatureF"));
            double humidity = Double.parseDouble(record.get("Humidity"));
            if (humidity >= value) {
                sum = currentTemp + sum;
                count = count + 1;
            }
        }
        if (count == 0) {
            return -1;
        }
        avg = sum / (double) count;
        return avg;
    }
    
    //Testing
    public void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord smallestSoFar = coldestHourInFile(parser);
        System.out.println(smallestSoFar.get("TemperatureF"));
    }

    public void testFileWithColdestTemperature() {
        String filePath = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + getFileName(filePath));
        FileResource fr = new FileResource(filePath);
        CSVParser parser = fr.getCSVParser();
        CSVParser parserSmall = fr.getCSVParser();
        CSVRecord smallestTempCSV = coldestHourInFile(parserSmall);
        System.out.println("Coldest temperature on that day was " + smallestTempCSV.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        for (CSVRecord record: parser) {
            String currentTemp = record.get("TemperatureF");
            String currentDate = record.get("DateUTC");
            System.out.println(currentDate + ": " + currentTemp);
        }
    }

    public void testlowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        String lowestHumidity = csv.get("Humidity");
        String time = csv.get("DateUTC");
        System.out.println("Lowest Humidity was " +lowestHumidity  + " at " + time);
    }

    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        String lowestHumidity = csv.get("Humidity");
        String time = csv.get("DateUTC");
        System.out.println("Lowest Humidity was " +lowestHumidity  + " at " + time);
    }

    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        averageTemperatureInFile(parser);
    }

    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double avg = averageTemperatureWithHighHumidityInFile(parser, value);
        if (avg == -1) {
            System.out.println("No temperatures with that humidity");
        }
        else {
        System.out.println("Average temperature when high humidity is " + avg);
        }
    }

    public static void main(String[] args) {
        ParsingWeatherData obj = new ParsingWeatherData();
        // obj.testColdestHourInFile();
        // obj.testFileWithColdestTemperature();
        // obj.testlowestHumidityInFile();
         obj.testLowestHumidityInManyFiles();
        // obj.testAverageTemperatureInFile();
        // obj.testAverageTemperatureWithHighHumidityInFile();
    }
}