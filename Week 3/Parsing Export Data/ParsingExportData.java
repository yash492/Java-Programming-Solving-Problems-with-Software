import edu.duke.*;
import org.apache.commons.csv.*;

public class ParsingExportData {
    public String countryInfo(CSVParser parser, String country) {
        for(CSVRecord record: parser) {
            String countries = record.get("Country");
            if (countries.contains(country)) {
                String export = record.get("Exports");
                String value = record.get("Value (dollars)");
                if (export.isEmpty() && value.isEmpty()) {
                    return "NOT FOUND";
                }
                return country + ": " + export + ": " + value;
            }
        }
        return "";
    }

    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record: parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem)) {
                count = count + 1;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser) {
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()) {
                String country = record.get("Country");
                System.out.println(country + " " + value);
            }
        }
    }

    public void testMethods() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVParser parser1 = fr.getCSVParser();
        CSVParser parser2 = fr.getCSVParser();
        CSVParser parser3 = fr.getCSVParser();

        String country = "Nauru";
        String countryInfo = countryInfo(parser, country);
        System.out.println(countryInfo);

        String export1 = "cotton";        
        String export2 = "flower";
        listExportersTwoProducts(parser1, export1, export2);

        String export = "cocoa";
        int count = numberOfExporters(parser2, export);
        System.out.println(count);

        bigExporters(parser3, "$999,999,999,999");
    }

    public static void main(String args[]) {
        ParsingExportData obj = new ParsingExportData();
        obj.testMethods();
    }
}