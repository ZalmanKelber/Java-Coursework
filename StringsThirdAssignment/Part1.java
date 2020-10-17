import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public int findStopCodon(String dna, int startIndex, String endCodon) {
        int currIndex = startIndex;
        while (true) {
            currIndex = dna.indexOf(endCodon, currIndex + 3);
            int mod = (currIndex - startIndex) % 3;
            //System.out.println("found currIndex " + currIndex + " " + mod);
            if (currIndex == -1) {
                return dna.length();
            }
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            }
        }
    }
    
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        return minIndex == dna.length() ? "" : dna.substring(startIndex, minIndex + 3);
        
    }
    
    public void printAllGenes(String dna) {
        System.out.println("testing: " + dna);
        int currIndex = 0;
        while (true) {
            String gene = findGene(dna.substring(currIndex));
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            currIndex += gene.length();
        }
    }
    
    public StorageResource getAllGenes(String dna) {
        StorageResource sr = new StorageResource();
        int currIndex = 0;
        while (currIndex < dna.length()) {
            String gene = findGene(dna.substring(currIndex));
            if (gene.isEmpty()) {
                break;
            }
            sr.add(gene);
            currIndex = dna.indexOf(gene,currIndex) + gene.length();
        }
        return sr;
    }
    
    public double cgRatio(String dna) {
        double numCsAndGs = 0.0;
        int i = 0;
        while (i < dna.length()) {
            if (dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                numCsAndGs += 1.0;
            }
            i++;
        }
        return numCsAndGs / ((double) dna.length());
        
    }
    
    public int countCTG(String dna) {
        int currIndex = 0;
        int count = 0;
        while (true) {
            currIndex = dna.indexOf("CTG", currIndex);
            if (currIndex == -1) {
                return count;
            }
            count++;
            currIndex += 3;
        }
    }
    
    public void processGenes(StorageResource sr) {
        int numLong = 0;
        int longest = 0;
        int numRatio = 0;
        int count = 0;
        for (String s : sr.data()) {
            count++;
            if (s.length() > 60) {
                System.out.println(s);
                numLong++;
            }
            if (s.length() > longest) {
                longest = s.length();
            }
        }
        for (String s : sr.data()) {
            if (cgRatio(s) > 0.35) {
                System.out.println(s);
                numRatio++;
            }
        }
        System.out.println("no of long strings = " + numLong);
        System.out.println("no of strings with high CG ratio = " + numRatio);
        System.out.println("longest length = " + longest);
        System.out.println("total strings = " + count);
    }
    
    public void testProcessGenes() {
        FileResource fr = new FileResource("dna.txt");
        String dna = fr.asString();
        processGenes(getAllGenes(dna.toUpperCase()));
        //System.out.print("CTG count = " + countCTG(dna.toUpperCase()));
    }
    
    public void testFindGene() {
        String[] strands = new String[]{"CCCGGGGCGCGCGCGCGCGCGCATGTAGATAGATAGAAAAATAGATAGATAG",
        "ATGAAAATAAATAAC", "CATGTAGATGTAAATGTGAAAAAA", "ATGTAGAATGATAAATGCCTAGCCGATGGGGTAG", "ATGTAG", "AAAAAAAATGATGATGCCCTAGTAA"};
        for (String dna : strands) {
            printAllGenes(dna);
        }
    }
    
    public void testFindStopCodon() {
        String dna = "ATGAAAATAAATAAC";
        String ec = "TAA";
        System.out.println("index for " + ec + " in " + dna + " = " + findStopCodon(dna, 2, ec));
        ec = "TAG";
        System.out.println("index for " + ec + " in " + dna + " = " + findStopCodon(dna, 0, ec));
        dna = "CCCGGGGCGCGCGCGCGCGCGCATGTAGATAGATAGAAAAATAGATAGATAG";
        System.out.println("index for " + ec + " in " + dna + " = " + findStopCodon(dna, 0, ec));  
    }
}
