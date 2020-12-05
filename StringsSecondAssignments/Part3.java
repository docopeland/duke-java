
/**
 * When inputting a dna string, it returns how many genes are in the string.
 * 
 * @author Danielle 
 * @version 13 July, 2020
 */
public class Part3 {
    public int findStopCodon(String dna, String stopCodon){
        int startIndex = dna.indexOf("ATG");
        int stopIndex = dna.indexOf(stopCodon, startIndex + 1);
        if (startIndex == -1) {stopIndex = -1;}
        while (stopIndex != -1) {
            if ((stopIndex - startIndex) % 3 == 0) {
                break;
            }
            else {
                stopIndex = dna.indexOf(stopCodon,stopIndex+1);
            }
        }
        return stopIndex;
    }
    public String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {return "";}
        int taaIndex = findStopCodon(dna,"TAA");
        int tagIndex = findStopCodon(dna,"TAG");
        int tgaIndex = findStopCodon(dna,"TGA");
        int minIndex = 0;
        if (taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)){
            minIndex = tagIndex;}
        else {minIndex = taaIndex;}
        if (minIndex == -1 || tgaIndex != -1 && tgaIndex < minIndex) {
            minIndex = tgaIndex;}
        if (minIndex == -1) {return "";}
        String gene = dna.substring(startIndex,minIndex+3);
        return gene;
    }
    public int countGenes(String dna) {
        int numb = 0;
        int index = 0;
        while (index < dna.length()) {
            String tempDna = dna.substring(index);
            String gene = findGene(tempDna);
            if (gene.isEmpty() == true) {break;}
            int geneInd = dna.indexOf(gene);
            index = geneInd + gene.length();
            numb = numb + 1;
        }
        return numb;
    }
    public void testCountGenes() {
        String dna1 = "ATGTAAGATGCCCTAGT"; // 2 genes
        System.out.println("DNA = " + dna1);
        System.out.println("Number of genes = " + countGenes(dna1));
        String dna2 = "ATGATAATCTAAATCCAG"; // 1 gene
        System.out.println("DNA = " + dna2);
        System.out.println("Number of genes = " + countGenes(dna2));
        String dna3 = "ATGATAATCTAATAGCAG"; // 1 gene
        System.out.println("DNA = " + dna3);
        System.out.println("Number of genes = " + countGenes(dna3));
        String dna4 = "ATGATAATCTACATCCAG"; // 0 genes
        System.out.println("DNA = " + dna4);
        System.out.println("Number of genes = " + countGenes(dna4));
    }
}
