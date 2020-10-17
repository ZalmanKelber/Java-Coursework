
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
