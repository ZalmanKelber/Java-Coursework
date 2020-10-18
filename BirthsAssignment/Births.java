
/**
 * Write a description of Births here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class Births {
    public void totalBirths(FileResource fr) {
        int maleBirths = 0;
        int femaleBirths = 0;
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            int births = Integer.parseInt(record.get(2));
            if (record.get(1).equals("M")) {
                maleBirths += births;
            } else {
                femaleBirths += births;
            }
        }
        System.out.println("total births: " + (maleBirths + femaleBirths));
        System.out.println("male births: " + maleBirths);
        System.out.println("female births: " + femaleBirths);
    }
    
    public int getRank(String name, int year, String gender) {
        int count = 0;
        String filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                count++;
                if (record.get(0).toLowerCase().equals(name.toLowerCase())) {
                    return count;
                }
            }
        }
        return -1;
    }
    
    public String getName(int rank, int year, String gender) {
        int count = 0;
        String filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                count++;
                if (count == rank) {
                    return record.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(name, year, gender);
        String newName = getName(rank, newYear, gender);
        System.out.println(name + " would be " + newName + " if born in " + newYear + " rather than " + year);
    }
    
    public int yearOfHighestRank(String name, String gender) {
        int highestRank = -1;
        int year = 1880;
        int countYear = 1879;
        while (countYear < 2014) {
            countYear++;
            int rankForYear = getRank(name, countYear, gender);
            //System.out.println(rankForYear + " " + highestRank);
            if ((rankForYear < highestRank && rankForYear != -1) || highestRank == -1) {
                year = countYear;
                highestRank = rankForYear;
            }
        }
        return highestRank != -1 ? year : -1;
    }
        public double getAverageRank(String name, String gender) {
        double totalRanks = 0.0;
        double totalYears = 0.0;
        int countYear = 1879;
        while (countYear < 2014) {
            countYear++;
            int rankForYear = getRank(name, countYear, gender);
            if (rankForYear != -1) {
                totalYears += 1.0;
                totalRanks += rankForYear;
            }
        }
        
        return totalYears == 0.0 ? -1.0 : totalRanks / totalYears;
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirths = 0;
        String filename = "us_babynames/us_babynames_by_year/yob" + year + ".csv";
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser(false);
        for (CSVRecord record : parser) {
            if (record.get(1).equals(gender)) {
                if (record.get(0).equals(name)) {
                    return totalBirths;
                }
                totalBirths += Integer.parseInt(record.get(2));
            }
        }
        return -1;
    }
    
    public void testGetTotalBirthsRankedHigher() {
        System.out.println("Total births ranked higher than Alex in 1988 are " + getTotalBirthsRankedHigher(1988, "Alex", "M"));
        System.out.println("Total births ranked higher than Emily in 1990 are " + getTotalBirthsRankedHigher(1990, "Emily", "F"));
    }
    
    public void testGetAverageRank() {
        String[] girlsnames = new String[]{"Mary", "Susan", "Florence", "Jessica", "Keisha", "Destiny", "Acxxsdea"};
        for (String name : girlsnames) {
            System.out.println("Average rank for " + name + " is " + getAverageRank(name, "F"));
        }
        System.out.println("Average rank for Robert is " + getAverageRank("Robert", "M"));
    }
    
    public void testYearOfHighestRank() {
        String[] girlsnames = new String[]{"Genevieve", "Florence", "Jessica", "Keisha", "Destiny", "Acxxsdea"};
        for (String name : girlsnames) {
            System.out.println("Year of highest rank for " + name + " is " + yearOfHighestRank(name, "F"));
        }
        System.out.println("Year of highest rank for Mich is " + yearOfHighestRank("Mich", "M"));
    }
    
    public void testWhatIsNameInYear() {
        int[] years = new int[]{1900, 1920, 1957, 1989, 2014};
        for (int year : years) {
            whatIsNameInYear("Owen", 1974, year, "M");
        }
    }
    
    public void testGetName() {
        System.out.println("name at rank 350 in 1980 is " + getName(350, 1980, "F"));
    }
    
    public void testGetRank() {
        System.out.println("rank of Susan in 1960 is " + getRank("Susan", 1960, "F"));
    }
    
    public void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
}
