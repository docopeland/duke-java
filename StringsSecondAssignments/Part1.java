
/**
 * Find a gene, no matter what the stop codon is.
 * 
 * @author Danielle 
 * @version 12 July, 2020
 */
public class Part1 {
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
    public void findGene(String dna) {
       int index = 0;
       String tempDna = dna;
       String gene = "";
        while (index < dna.length()) {
            int startIndex = tempDna.indexOf("ATG");
            if (startIndex == -1) {
                System.out.println("Gene = ");
                break;
            }
            int taaIndex = findStopCodon(tempDna,"TAA");
            int tagIndex = findStopCodon(tempDna,"TAG");
            int tgaIndex = findStopCodon(tempDna,"TGA");
            int minIndex = 0;
            if (taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)){
                minIndex = tagIndex;}
                else {minIndex = taaIndex;}
            if (minIndex == -1 || tgaIndex != -1 && tgaIndex < minIndex) 
                {minIndex = tgaIndex;}
            if (minIndex == -1) {
                System.out.println("Gene = ");
                break;
            }
            gene = tempDna.substring(startIndex,minIndex + 3);
            System.out.println("Gene = " + gene);
            tempDna = tempDna.substring(minIndex + 3);
        }
    }
    public void testfindStopCodon() {
        String stopCodon = "TAA";
        String dna1 = "ATGTAA";
        System.out.println(findStopCodon(dna1,stopCodon));
        String dna2 = "ATGATAATC";
        System.out.println(findStopCodon(dna2,stopCodon));
        String dna3 = "ATGATAATCTAA";
        System.out.println(findStopCodon(dna3,stopCodon));
        String dna4 = "ATGCAG";
        System.out.println(findStopCodon(dna4,stopCodon));
    }
    public void testFindGene() {
        String dna1 = "ATCATAATCTAAATCCAG"; // no ATG
        System.out.println("DNA = " + dna1);
        findGene(dna1);
        String dna2 = "ATGATAATCTAAATCCAG"; //ATG && TAA (valid)
        System.out.println("DNA = " + dna2);
        findGene(dna2);
        String dna3 = "ATGATAATCTAATAGCAG"; // ATG && TAA && TAG
        System.out.println("DNA = " + dna3);
        findGene(dna3);
        String dna4 = "ATGATAATCTACATCCAG"; //ATG && no stopCodon
        System.out.println("DNA = " + dna4);
        findGene(dna4);
        String dna5 = "ATGATAATTAAATCCAG"; //ATG && TAA (not valid)
        System.out.println("DNA = " + dna5);
        findGene(dna5);
        String dna6 ="ATGTAAGATGCCCTAGT"; // 2 genes
        System.out.println("DNA = " + dna6);
        findGene(dna6);
        String dna7 = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA = " + dna7);
        findGene(dna7);
    }
}

