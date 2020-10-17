
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;
public class Part1 {
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldest = null;
        for (CSVRecord record : parser) {
            if (coldest == null) {
                coldest = record;
            } else {
                double temp = Double.parseDouble(record.get("TemperatureF"));
                if (temp != -9999 && temp < Double.parseDouble(coldest.get("TemperatureF"))) {
                    coldest = record;
                }
            }
        }
        return coldest;
    }
    
    public File findColdestOfTwo(File file1, File file2) {
        FileResource fr1 = new FileResource(file1);
        FileResource fr2 = new FileResource(file2);
        CSVRecord coldest1 = coldestHourInFile(fr1.getCSVParser());
        CSVRecord coldest2 = coldestHourInFile(fr2.getCSVParser());
        Double temp1 = Double.parseDouble(coldest1.get("TemperatureF"));
        Double temp2 = Double.parseDouble(coldest2.get("TemperatureF"));
        return temp2 < temp1 ? file2 : file1;
    }
    
    public File fileWithColdestHour(DirectoryResource dr) {
        File coldest = null;
        for (File f : dr.selectedFiles()) {
            if (coldest == null) {
                coldest = f;
            } else {
                coldest = findColdestOfTwo(coldest, f);
            }
        }
        return coldest;
    }
    
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowest = null;
        for (CSVRecord record : parser) {
            if (lowest == null) {
                lowest = record;
            } else {
                String hum1 = lowest.get("Humidity");
                String hum2 = record.get("Humidity");
                if (!hum2.equals("N/A")) {
                   if (hum1.equals("N/A")) {
                       lowest = record;
                   } else {
                       int h1 = Integer.parseInt(hum1);
                       int h2 = Integer.parseInt(hum2);
                       lowest = h2 < h1 ? record : lowest;
                   }
                }
            }
        }
        return lowest;
    }
    
    public CSVRecord lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowest = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord lowestInFile = lowestHumidityInFile(parser);
            if (lowest == null) {
                lowest = lowestInFile;
            } else {
                int h1 = Integer.parseInt(lowest.get("Humidity"));
                int h2 = Integer.parseInt(lowestInFile.get("Humidity"));
                lowest = h2 < h1 ? lowestInFile : lowest;
            }
        }
        return lowest;
    }
    
    public double averageTemperatureInFile(CSVParser parser) {
        double total = 0.0;
        int numRecords = 0;
        for (CSVRecord record : parser) {
           double temp = Double.parseDouble(record.get("TemperatureF"));
           if (temp != -9999) {
               numRecords++;
               total += temp;
           }
        }
        return total / numRecords;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double total = 0.0;
        int numRecords = 0;
        for (CSVRecord record : parser) {
           double temp = Double.parseDouble(record.get("TemperatureF"));
           String hum = record.get("Humidity");
           if (temp != -9999 && !hum.equals("N/A") && Integer.parseInt(hum) >= value) {
               numRecords++;
               total += temp;
           }
        }
        return total / numRecords;
    }
    
    public void testAverageTemperatureWithHighHumidityInFile() {
        System.out.println("running test:");
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double d = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("average temperature in file with high humidity is " + d);
    }
    
    public void testAverageTemperatureInFile() {
        System.out.println("running test:");
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double d = averageTemperatureInFile(parser);
        System.out.println("average temperature in files is " + d);
    }
    
    public void testLowestHumidityInManyFiles() {
        CSVRecord csv = lowestHumidityInManyFiles();
        System.out.println("lowest humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("lowest humidity was " + csv.get("Humidity") + " at " + csv.get("DateUTC"));
    }
    
    public void testFileWithColdestHour() {
        System.out.println("running test:");
        DirectoryResource dr = new DirectoryResource();
        File f = fileWithColdestHour(dr);
        FileResource fr = new FileResource(f);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser) {
            System.out.println(f.getName() + " " + record.get("TimeEST") + ": " + record.get("TemperatureF"));
        }
    }
    
    public void testColdestHour() {
        System.out.println("running test:");
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord hour = coldestHourInFile(parser);
        System.out.println(hour.get("DateUTC"));
        System.out.println(hour.get("TemperatureF"));
    }
}
