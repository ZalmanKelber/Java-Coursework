public class Part1 {
    public String findSimpleGene(String dna) {
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1) {
            return "";
        }
        int endCodon = dna.indexOf("TAA", startCodon);
        if (endCodon == -1 || (endCodon - startCodon) % 3 != 0) {
            return "";
        }
        return dna.substring(startCodon, endCodon + 3);
    }
    
    public void testSimpleGene() {
        String[] dnaStrs = new String[]{"ATGAAAAATAAT", "AAAAATAA", "ATGAAAAAA", "CAAAAAATGACAACAACATAAA", "C"};
        for (String dna : dnaStrs) {
            System.out.println("input: " + dna);
            String gene = findSimpleGene(dna);
            if (gene.length() == 0) {
                gene = "(none)";
            }
            System.out.println("found gene: " + gene);
        }
    }
}
