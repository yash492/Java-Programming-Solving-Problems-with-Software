import edu.duke.*;
import org.apache.commons.csv.*;

public class BabyBirths {
    public void printNames() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser(false);
        for(CSVRecord record: parser) {
            String names = record.get(0);
            String gender = record.get(1);
            String numBorn = record.get(2);
            System.out.println("Name: " + names + " Gender: " + gender + " Num Born: " + numBorn);
        }
    }

    public void totalBirths(FileResource fr) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        for(CSVRecord record: fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            if (record.get(1).equals("F")) {
                totalGirls += numBorn;
            }
            else {
                totalBoys += numBorn;
            }
        }
        System.out.println(totalBirths);
        System.out.println(totalBoys);
        System.out.println(totalGirls);

    }

    public void testTotalBirths() {
        FileResource fr = new FileResource("data/us_babynames_test/example-small.csv");
        totalBirths(fr);
    }

    public static void main(String[] args) {
        BabyBirths obj = new BabyBirths();
        //obj.printNames();
        obj.testTotalBirths();
    }
}
