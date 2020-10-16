
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public boolean twoOccurances(String stringa, String stringb) {
        int index1 = stringb.indexOf(stringa);
        if (index1 == -1 || index1 + stringa.length() * 2 > stringb.length()) {
            return false;
        }
        return stringb.indexOf(stringa, index1 + stringa.length()) >= 0;
    }
    
    public String lastPart(String stringa, String stringb) {
        int index = stringb.indexOf(stringa);
        if (index == -1 || index + stringa.length() > stringb.length()) {
            return stringb;
        }
        return stringb.substring(index + stringa.length(), stringb.length());
    }
    
    public void testing() {
        String a = "abc";
        String b = "abcabc";
        System.out.println("the part of " + b + " after " + a + " is " + lastPart(a, b));
        a = "";
        b = "two";
        System.out.println("the part of " + b + " after " + a + " is " + lastPart(a, b));
        a = "example";
        b = "example";
        System.out.println("the part of " + b + " after " + a + " is " + lastPart(a, b));
        a = "longexample";
        b = "longexamples";
        System.out.println("the part of " + b + " after " + a + " is " + lastPart(a, b));
    }
}
