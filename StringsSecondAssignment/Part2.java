
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringb, String stringa) {
        if (stringa.isEmpty() || stringb.isEmpty()) {
            return 0;
        }
        int currIndex = 0;
        int count = 0;
        while (true) {
            //System.out.println("current index before: " + currIndex);
            currIndex = stringb.indexOf(stringa, currIndex);
            //System.out.println("current index after: " + currIndex);
            if (currIndex == -1) {
                return count;
            }
            count++;
            currIndex += stringa.length();
        }
    }
    
    public void testHowMany() {
        String a = "abcabcabc";
        String b = "ab";
        System.out.println("testing " + a + " and " + b + ": " + howMany(a,b));
        a = "";
        b = "";
        System.out.println("testing " + a + " and " + b + ": " + howMany(a,b));
        a = "adaddaddddaddddddddadddddddd";
        b = "ad";
        System.out.println("testing " + a + " and " + b + ": " + howMany(a,b));
        a = "aaaacaaaaaaaacaaaaaaa";
        b = "aaaacaaaaaaaa";
        System.out.println("testing " + a + " and " + b + ": " + howMany(a,b));
    }
}
