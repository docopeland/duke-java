
/**
 * This class allows the user to find the gene string (if any) that is located
 * within a string of DNA.
 * 
 * @author Danielle 
 * @version 11 July, 2020
 */
public class Part1 {
    public String findSimpleGene(String dna) {
        // store indexOf for startCodon
        int startIndex = dna.indexOf("ATG");
        // check if startIndex is empty; if yes, return nothing
        if (startIndex == -1) {return "";}
        // store indexOf for stopCodon
        int stopIndex = dna.indexOf("TAA");
        // check if stopIndex is emtpy; if yes, return nothing
        if (stopIndex == -1) {return "";}
        // check if the length isn't multiple of 3; if yes, return substring
        if (((stopIndex + 3) - startIndex) %3 != 0) {return "";}
        return dna.substring(startIndex,stopIndex+3);
    }
    public void testSimpleGene() {
        String a = "TACTAGCATATACCTAAATC"; // DNA with no ATG but TAA
        System.out.println("DNA = " + a); // print DNA 
        System.out.println("Gene = " + findSimpleGene(a)); // print gene
        String b = "TACTATGCATATACCTATC"; // DNA with no TAA but ATG
        System.out.println("DNA = " + b); // print DNA 
        System.out.println("Gene = " + findSimpleGene(b)); // print gene
        String c = "TACTATCGCATATACCTATC"; // string with no ATG or TAA
        System.out.println("DNA = " + c); // print DNA 
        System.out.println("Gene = " + findSimpleGene(c)); // print gene
        String d = "TACTATGCGCATATAACCTATC"; // string of ATG & TAA % 3 == 0
        System.out.println("DNA = " + d); // print DNA 
        System.out.println("Gene = " + findSimpleGene(d)); // print gene
        String e = "TACTATGCGTCATATAACCTATC"; // string of ATG & TAA % 3 != 0
        System.out.println("DNA = " + e); // print DNA 
        System.out.println("Gene = " + findSimpleGene(e)); // print gene 
    }
}
