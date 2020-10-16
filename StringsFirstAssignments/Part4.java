import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void parseLinks(String url) {
        String query = "youtube.com";
        URLResource ur = new URLResource(url);
        for (String s : ur.lines()) {
            //System.out.println(s);
            int index = s.toLowerCase().indexOf(query);
            if (index > 0) {
                int start = s.lastIndexOf("\"", index);
                int end = s.indexOf("\"", index) + 1;
                System.out.println(s.substring(start, end));
            }
        }
    }
    
    public void testing() {
        String url = "https://www.dukelearntoprogram.com//course2/data/manylinks.html";
        parseLinks(url);
    }
}
