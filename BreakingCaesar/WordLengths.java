
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class WordLengths {
    public int[] countWordLengths(FileResource resource, int [] counts) {
        for (String s : resource.words()) {
            s = s.toLowerCase();
            StringBuilder sb = new StringBuilder(s);
            char fDig = sb.charAt(0);
            char lDig = sb.charAt(s.length()-1);
            int sLength = s.length();
            if (Character.isLetter(fDig) == false) {
                sLength = sLength - 1;
            }
            if (Character.isLetter(lDig) == false){
                sLength = sLength - 1;
            }
            if (sLength == -1) {
                counts[0]++;
            }
            else {
                    if (sLength > counts.length) {
                        counts[counts.length-1]++;
                    }
                    else {
                        counts[sLength]++;
                }
            }
        }
        for (int k = 0; k < counts.length; k++) {
            if (counts[k] != 0) {
                if (counts[k] == 1) {
                    System.out.println(counts[k] +" word of length " + k);
                }
                else {
                    System.out.println(counts[k] + " words of length " + k);
                }
            }
        }
        return counts;
    }
    public void indexOfMax(int [] values) {
        int biggestNo = 0;
        int biggestK = 0;
        for (int k = 0; k < values.length; k++) {
            int current = values[k];
            if (current > biggestNo) {
                biggestNo = current;
                biggestK = k;
            }
        }
        System.out.println("The most common word length is " + biggestK);
    }
    public void testCountWordLengths() {
        FileResource resource = new FileResource();
        int [] counts = new int [31];
        int [] values = countWordLengths(resource,counts);
        indexOfMax(values);
    }
}
