import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class BabyNames {
    public void totalBirths (int year) {
        int totalBirth = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        int totalNames = 0;
        int totalNamesGirls = 0;
        int totalNamesBoys = 0;

        CSVParser parser = getParserByYear(year);

        for(CSVRecord rec: parser) {
            int numBirth = Integer.parseInt(rec.get(2));
            totalBirth += numBirth;
            if (rec.get(1).contains("F")) {
                totalGirls += numBirth;
                totalNamesGirls += 1;
            }
            else {
                totalBoys += numBirth;
                totalNamesBoys += 1;
            }
            totalNames += 1;
        }
        System.out.println("The total number of Births: " + totalBirth);
        System.out.println("The total number of Boys: " + totalBoys);
        System.out.println("The total number of Girls: " + totalGirls);
        System.out.println("The total number of Names: " + totalNames);
        System.out.println("The total number of Boys' Names: " + totalNamesBoys);
        System.out.println("The total number of Girls' Names: " + totalNamesGirls);
    }

    public int getRank (int year, String name, String gender) {
        int rankBirths = 0;
        CSVParser parser = getParserByYear(year);
        for (CSVRecord rec: parser) {
            if (rec.get(1).equals(gender)) {
                rankBirths += 1;
                if (rec.get(0).equals(name)){
                    return rankBirths;
                }
            }
        }
        return -1;
    }

    public String getName (int year, int rank, String gender) {
        CSVParser parser = getParserByYear(year);
        int rankBirths = 0;
        for(CSVRecord rec: parser) {
            if (rec.get(1).equals(gender)) {
                rankBirths+=1;
                if (rankBirths == rank){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    public void whatIsNameInYear (String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        System.out.println(name + " born in " + year + " would be " + newName + 
        " if she was born in " + newYear +".");
    }

    public int yearOfHighestRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int highestRank = -1;
        File highestRankFile = null;
        String year = "";
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int rankBirths = 0;
            for (CSVRecord rec: parser) {
                if (rec.get(1).equals(gender)) {
                    rankBirths += 1;   
                    if (rec.get(0).equals(name)) {
                        if (highestRank == -1) {
                            highestRank = rankBirths;
                        }
                        if (highestRankFile == null) {
                            highestRankFile = f;
                        }
                        else {
                            if (rankBirths < highestRank) {
                                highestRank = rankBirths;
                                highestRankFile = f;
                            }
                        }
                    }
                }
            }
        }
        if (highestRank == -1){
            return -1;
        }
        year = highestRankFile.getName().substring(3, 7);

        return Integer.parseInt(year);    
    }

    public double getAverageRank (String name, String gender) {
        DirectoryResource dr = new DirectoryResource();
        int count = 0;
        int totalRank = 0;
        double avg = 0;
        for (File f: dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser(false);
            int rank = 0;
            for (CSVRecord rec: parser) {
                if (rec.get(1).equals(gender)) {
                    rank += 1;
                    if (rec.get(0).equals(name)) {
                        totalRank += rank;
                        count += 1;
                    }
                }
            }
        }
        avg = (double) totalRank/count;
        return avg;
    }

    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        CSVParser parser = getParserByYear(year);
        int totalBirths = 0;
        for (CSVRecord rec: parser) {
            if (rec.get(1).equals(gender)) {
                int numBirth = Integer.parseInt(rec.get(2));
                if (rec.get(0).equals(name)){
                    break;
                }
                totalBirths += numBirth;
            }

        }
        return totalBirths;
    }
    
    // Testing
    public void testTotalBirths () {
        int year = 1905;
        totalBirths(year);
    }

    public void testGetRank () {
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        int rank = getRank(year, name, gender);
        System.out.println(rank);

    }

    public void testGetName () {
        int year = 1982;
        int rank = 450;
        String gender = "M";

        String name = getName(year, rank , gender);
        System.out.println(name);

    }

    public void testWhatIsNameInYear () {
        String name = "Owen";
        int year = 1974;
        int newYear = 2014;
        String gender = "M";
        whatIsNameInYear(name, year, newYear, gender);
    }

    public void testYearOfHighestRank (){
        String name = "Mich";
        String gender = "M";
        int year = yearOfHighestRank(name, gender);
        System.out.println(year);
    }

    public void testGetAverageRank (){
        String name = "Susan";
        String gender = "F";
        double avg = getAverageRank(name, gender);
        System.out.println(avg);
    }

    public void testGetTotalBirthsRankedHigher () {
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        int totalBirthsRankedHigher = getTotalBirthsRankedHigher(year, name, gender);
        System.out.println(totalBirthsRankedHigher);
    }

    public CSVParser testGetParser (int year) {
        String fileName = "data\\" + "us_babynames_test\\"+"yob" + year + "short" + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        return parser;
    }

    // Helper Function
    public CSVParser getParserByYear (int year) {
        String fileName = "data\\" + "us_babynames_by_year\\"+"yob" + year + ".csv";
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser(false);
        return parser;
    }


    public static void main(String[] args) {
        BabyNames obj = new BabyNames();
        // obj.testTotalBirths();
        // obj.testGetRank();
        // obj.testGetName();
        // obj.testWhatIsNameInYear();
        // obj.testYearOfHighestRank();
         obj.testGetAverageRank();
        // obj.testGetTotalBirthsRankedHigher();
    }

}