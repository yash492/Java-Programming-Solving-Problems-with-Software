import edu.duke.*;
import org.apache.commons.csv.*;

public class WhichCountriesExport {
    public void listExporter(CSVParser parser, String exportOfInterest) {
        for (CSVRecord record: parser) {
            String export = record.get("Exports");
            //export.indexOf(exportOfInterest) != 1
            if (export.contains(exportOfInterest)) {
                String countries = record.get("Country");
                System.out.println(countries);
            }
        }
    }

    public void whoExportsCoffee() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        String exportOfInterest = "coffee";
        listExporter(parser, exportOfInterest);
    }

    public static void main(String[] args) {
        WhichCountriesExport obj = new WhichCountriesExport();
        obj.whoExportsCoffee();
    }
}
