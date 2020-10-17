
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").toLowerCase().equals(country.toLowerCase())) {
                return record.get("Country") + ": " + record.get("Exports") + ": " + record.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get("Country"));
            }
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int count = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").toLowerCase().contains(exportItem.toLowerCase())) {
                count++;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String value) {
        for (CSVRecord record : parser) {
            if (record.get("Value (dollars)").length() > value.length()) {
                System.out.println(record.get("Country").toUpperCase());
            }
        }
    }
    
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser, "Nauru"));
        parser = fr.getCSVParser();
        //listExportersTwoProducts(parser, "cotton", "flowers");
        parser = fr.getCSVParser();
        //System.out.println(numberOfExporters(parser, "cocoa"));
        parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
    }
}
