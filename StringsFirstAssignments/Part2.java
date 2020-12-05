
/**
 * This class allows the user to find the gene string (if any) that is located
 * within a string of DNA. Also allows for the DNA to be written in lowercase.
 * 
 * @author Danielle 
 * @version 11 July, 2020
 */
public class Part2 {
    public Boolean geneUpperCase(String dna) {
        // check if can find A, T, G, or C; if yes, return true
        if ((dna.contains("A") == true) || (dna.contains("T") == true) 
        || (dna.contains("G") == true) || (dna.contains("C") == true)) {
            return true;
        }
        else { return false; }
    }
    public String 
    findSimpleGene(String dna,String startCodon,String stopCodon) {
        if (geneUpperCase(dna) == false) {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        // store indexOf for startCodon
        int startIndex = dna.indexOf(startCodon);
        // check if startIndex is empty; if yes, return nothing
        if (startIndex == -1) {return "";}
        // store indexOf for stopCodon
        int stopIndex = dna.indexOf(stopCodon,startIndex);
        // check if stopIndex is emtpy; if yes, return nothing
        if (stopIndex == -1) {return "";}
        // check if the length isn't multiple of 3; if yes, return nothing
        if (((stopIndex+3) - startIndex) %3 != 0) {return "";}
        // return the substring
        return dna.substring(startIndex,stopIndex+3);
    }
    public void testSimpleGene() {
        String s1 = "ATG";
        String s2 = "TAA";
        String a = "TACTAGCATATACCTAAATC"; // DNA with no ATG but TAA
        System.out.println("DNA = " + a); // print DNA 
        System.out.println("Gene = " + findSimpleGene(a,s1,s2)); // print gene
        String b = "TACTATGCATATACCTATC"; // DNA with no TAA but ATG
        System.out.println("DNA = " + b); // print DNA 
        System.out.println("Gene = " + findSimpleGene(b,s1,s2)); // print gene
        String c = "TACTATCGCATATACCTATC"; // string with no ATG or TAA
        System.out.println("DNA = " + c); // print DNA 
        System.out.println("Gene = " + findSimpleGene(c,s1,s2)); // print gene
        String d= "TACTATGCGTCATATAACCTATC"; // string of ATG & TAA % 3 != 0
        System.out.println("DNA = " + d); // print DNA 
        System.out.println("Gene = " + findSimpleGene(d,s1,s2)); // print gene
        String g= "TACTATACGTCATATAACCTATG"; // string of TAA before ATG
        System.out.println("DNA = " + g); // print DNA 
        System.out.println("Gene = " + findSimpleGene(g,s1,s2)); // print gene
        String e = "TACTATGCGCATATAACCTATC"; // string of good gene
        System.out.println("DNA = " + e); // print DNA 
        System.out.println("Gene = " + findSimpleGene(e,s1,s2)); // print gene
        String f = "tactatgcgcatataacctatc"; // lowercase good gene
        System.out.println("DNA = " + f); // print DNA
        System.out.println("Gene = " + findSimpleGene(f,s1,s2)); // print gene
    }
}
