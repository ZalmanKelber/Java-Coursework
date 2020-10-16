
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String endCodon) {
        boolean isLower = dna == dna.toLowerCase();
        if (isLower) {
            startCodon = startCodon.toLowerCase();
            endCodon = endCodon.toLowerCase();
        }
        int startCodonIndex = dna.indexOf(startCodon);
        if (startCodonIndex == -1) {
            return "";
        }
        int endCodonIndex = dna.indexOf(endCodon, startCodonIndex);
        if (endCodonIndex == -1 || (endCodonIndex - startCodonIndex) % 3 != 0) {
            return "";
        }
        return dna.substring(startCodonIndex, endCodonIndex + 3);
    }
    
    public void testSimpleGene() {
        String[] dnaStrs = new String[]{"ATGAAAAATAAT", "AAAAATAA", "caaaaaaaatgacaacaaacccataaa", "CAAAAAATGACAACAACATAAA", "C"};
        String startCodon = "ATG";
        String endCodon = "TAA";
        for (String dna : dnaStrs) {
            System.out.println("input: " + dna);
            String gene = findSimpleGene(dna, startCodon, endCodon);
            if (gene.length() == 0) {
                gene = "(none)";
            }
            System.out.println("found gene: " + gene);
        }
    }
}
