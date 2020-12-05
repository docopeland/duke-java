
/**
 * This class prints out genes for a DNA string from a file.
 * 
 * @author Danielle
 * @version 13 July, 2020
 */

import edu.duke.*;

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
    public void printAllGenes(String dna) {
       String tempDna = dna.toUpperCase();
       String gene = "";
        while (true) {
            int startIndex = tempDna.indexOf("ATG");
            if (startIndex == -1) {
                System.out.println("Gene = ");
                break;
            }
            int taaIndex = findStopCodon(tempDna,"TAA");
            System.out.println(taaIndex);
            int tagIndex = findStopCodon(tempDna,"TAG");
            System.out.println(tagIndex);
            int tgaIndex = findStopCodon(tempDna,"TGA");
            System.out.println(tgaIndex);
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
            System.out.println(minIndex);
            gene = tempDna.substring(startIndex,minIndex + 3);
            System.out.println("Gene = " + gene);
            tempDna = tempDna.substring(minIndex + 3);
        }
    }
    public StorageResource getAllGenes(String dna) {
       String tempDna = dna.toUpperCase();
       String gene = "";
       StorageResource dnaStore = new StorageResource();
       while (true) {
            int startIndex = tempDna.indexOf("ATG");
            if (startIndex == -1) {break;}
            int taaIndex = findStopCodon(tempDna,"TAA");
            int tagIndex = findStopCodon(tempDna,"TAG");
            int tgaIndex = findStopCodon(tempDna,"TGA");
            int minIndex = 0;
            if (taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)){
                minIndex = tagIndex;}
                else {minIndex = taaIndex;}
            if (minIndex == -1 || tgaIndex != -1 && tgaIndex < minIndex) 
                {minIndex = tgaIndex;}
            if (minIndex == -1) {break;}
            gene = tempDna.substring(startIndex,minIndex + 3);
            dnaStore.add(gene);
            tempDna = tempDna.substring(minIndex + 3);
       }
       return dnaStore;
    }
    public double cgRatio(String dna) {
        double ratio = 0.0;
        int indexC = 0;
        int indexG = 0;
        int cCount = 0;
        int gCount = 0;
        while(true) {
            indexC = dna.indexOf("C",indexC);
            if (indexC == -1) {break;}
            cCount = cCount + 1;
            indexC = indexC + 1;
        }
        while (true) {
            indexG = dna.indexOf("G",indexG);
            if (indexG == -1 ) {break;}
            gCount = gCount + 1;
            indexG = indexG + 1;
        }
        ratio = ((double)cCount+gCount)/dna.length();
        return ratio;
    }
    public int countCTG(String dna) {
        int ctgCount = 0;
        int indexCtg = 0;
        while (true) {
            indexCtg = dna.indexOf("CTG",indexCtg);
            if (indexCtg == -1) {break;}
            ctgCount = ctgCount + 1;
            indexCtg = indexCtg + 3;
        }
        return ctgCount;
    }
    public void processGenes(StorageResource sr) {
        int count9 = 0;
        int countCg = 0;
        int longGene = 0;
        int count = 0;
        for (String s : sr.data()) {
            // print out string for longer than 9
            if (s.length() > 60) {
                System.out.println(
                "Gene is longer than 60: " + s);
                count9++;
            }
            // print out string for higher than 0.35 cgRatio
            if (cgRatio(s) > 0.35) {
                System.out.println(
                "Gene has a CG Ratio higher than 0.35: " + s);
                countCg++;
            }
            int currLen = s.length();
            if (currLen > longGene) {longGene = currLen;}
            count++;
        }
        // print out count of longer than 9
        System.out.println("# of strings longer than 60: " + count9);
        // print out count of higher than 0.35 ratio
        System.out.println("# of strings with over 0.35 cg: " + countCg);
        // print out count of the longest gene
        System.out.println("Length of longest gene : " + longGene);
        System.out.println("number of genes : " + count);
    }
    public void testBfile() {
        int count = 0;
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        String dna = fr.asString();
        processGenes(getAllGenes(dna));
        System.out.println(countCTG(dna));
    }
    public void testProcessGenes() {
        StorageResource sr = new StorageResource();
        String dna1 = "ATGCCATAG"; // higer than 0.35
        String dna2 = "ATGCCTGCACTGTAGCTGCTG"; // gene longer than 9
        String dna3 = "ATGTAAGATGCCCTAGT"; //6 char gene & 9 char gene
        String dna4 = "ATGTAA"; // lower than 0.35
        String dna5 = "ATGATAATCTAATAGCAG";
        System.out.println("DNA: " + dna1);
        processGenes(getAllGenes(dna1));
        System.out.println("DNA: " + dna2);
        processGenes(getAllGenes(dna2));
        System.out.println("DNA: " + dna3);
        processGenes(getAllGenes(dna3));
        System.out.println("DNA: " + dna4);
        processGenes(getAllGenes(dna4));
        System.out.println("DNA: " + dna5);
        processGenes(getAllGenes(dna5));
        /** Write a method testProcessGenes. This method will call your 
         * processGenes method on different test cases. Think of five DNA 
         * strings to use as test cases. These should include: 
         * -one DNA string that has some genes longer than 9 characters, 
         * -one DNA string that has no genes longer than 9 characters, 
         * -one DNA string that has some genes whose C-G-ratio is higher 
         * than 0.35, 
         * -one DNA string that has some genes whose C-G-ratio is lower 
         * than 0.35. 
         * Write code in testProcessGenes to call processGenes five times 
         * with StorageResources made from each of your five DNA string 
         * test cases.
         */
    }
    public void testCgRatio () {
        System.out.println(cgRatio("ATGCCATAG"));
        System.out.println(countCTG("ATGCCTGCACTGTAGCTGCTG"));
    }
    public void testPrintAllGenes() {
        String dna1 = "ATCATAATCTAAATCCAG"; // no ATG
        System.out.println("DNA = " + dna1);
        printAllGenes(dna1);
        String dna2 = "ATGATAATCTAAATCCAG"; //ATG && TAA (valid)
        System.out.println("DNA = " + dna2);
        printAllGenes(dna2);
        String dna3 = "ATGATAATCTAATAGCAG"; // ATG && TAA && TAG
        System.out.println("DNA = " + dna3);
        printAllGenes(dna3);
        String dna4 = "ATGATAATCTACATCCAG"; //ATG && no stopCodon
        System.out.println("DNA = " + dna4);
        printAllGenes(dna4);
        String dna5 = "ATGATAATTAAATCCAG"; //ATG && TAA (not valid)
        System.out.println("DNA = " + dna5);
        printAllGenes(dna5);
        String dna6 ="ATGTAAGATGCCCTAGT"; // 2 genes
        System.out.println("DNA = " + dna6);
        printAllGenes(dna6);
        String dna7 = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA = " + dna7);
        printAllGenes(dna7);
    }
    public void testGetAllGenes(String dna) {
        StorageResource sr = getAllGenes(dna);
        for (String s : sr.data()) {
            System.out.println("Gene = " + s);
        }
    }
    public void test() {
        String dna1 = "ATCATAATCTAAATCCAG"; // no ATG
        System.out.println("DNA = " + dna1);
        testGetAllGenes(dna1);
        String dna2 = "ATGATAATCTAAATCCAG"; //ATG && TAA (valid)
        System.out.println("DNA = " + dna2);
        testGetAllGenes(dna2);
        String dna3 = "ATGATAATCTAATAGCAG"; // ATG && TAA && TAG
        System.out.println("DNA = " + dna3);
        testGetAllGenes(dna3);
        String dna4 = "ATGATAATCTACATCCAG"; //ATG && no stopCodon
        System.out.println("DNA = " + dna4);
        testGetAllGenes(dna4);
        String dna5 = "ATGATAATTAAATCCAG"; //ATG && TAA (not valid)
        System.out.println("DNA = " + dna5);
        testGetAllGenes(dna5);
        String dna6 ="ATGTAAGATGCCCTAGT"; // 2 genes
        System.out.println("DNA = " + dna6);
        testGetAllGenes(dna6);
        String dna7 = "AATGCTAACTAGCTGACTAAT";
        System.out.println("DNA = " + dna7);
        testGetAllGenes(dna7);
    }
}
