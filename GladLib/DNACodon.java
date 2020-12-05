
/**
 * Write a description of DNACodon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class DNACodon {
    private HashMap<String, Integer> DNACounts;
    public DNACodon() {
        DNACounts = new HashMap<String, Integer>();
    }
    private void buildCodonMap(String dna, int start) {
        DNACounts.clear();
        for (int k = start; k < dna.length()-3; k = k + 3) {
            String codon = dna.substring(k,k+3);
            if (DNACounts.containsKey(codon)) {
                DNACounts.put(codon,DNACounts.get(codon)+1);
            }
            else {
                DNACounts.put(codon,1);
            }
        }
    }
    private String getMostCommonCodon() {
        int commonNo = 0;
        String commonNa = "";
        for (String s : DNACounts.keySet()) {
            int val = DNACounts.get(s);
            if (val > commonNo) {
                commonNo = val;
                commonNa = s;
            }
        }
        return commonNa;
    }
    private void printCodonCounts(int start, int end) {
        for (String s : DNACounts.keySet()) {
            int val = DNACounts.get(s);
            //if (start <= val && val <= end) {
                System.out.println(s + "\t" + val);
            //}
        }
    }
    public void test() {
        FileResource fr = new FileResource();
        String dna = fr.asString().toUpperCase();
        //reading frame 0
        buildCodonMap(dna, 0);
        System.out.println("For rf0 there are " + DNACounts.size() + " unique codons");
        System.out.println("The most common codon is " + getMostCommonCodon());
        printCodonCounts(1, 5);
        //reading frame 1
        buildCodonMap(dna, 1);
        System.out.println("For rf1 there are " + DNACounts.size() + " unique codons");
        System.out.println("The most common codon is " + getMostCommonCodon());
        printCodonCounts(1, 5);
        //reading frame 2
        buildCodonMap(dna, 2);
        System.out.println("For rf2 there are " + DNACounts.size() + " unique codons");
        String com = getMostCommonCodon();
        System.out.println("The most common codon is " + com + " with " + 
        DNACounts.get(com) + " counts.");
        printCodonCounts(1, 5);
    }
}
