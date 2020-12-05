
/**
 * Write a description of WordFrequencies here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import edu.duke.*;
public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    private void findUnique() {
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for (String word : fr.words()) {
            word = word.toLowerCase();
            if (! myWords.contains(word)) {
                myWords.add(word);
                myFreqs.add(0);
            }
        }
        for (String word : fr.words()) {
            word = word.toLowerCase();
            if (myWords.contains(word)) {
                int k = myWords.indexOf(word);
                int value = myFreqs.get(k) + 1;
                myFreqs.set(k, value);
            }
        }
        this.myFreqs = myFreqs;
        this.myWords = myWords;
    }
    public void testFindUnique() {
        findUnique();
        System.out.println("The number of unique words is " + myWords.size());
        // for (int k = 0; k < myWords.size(); k++) {
            // System.out.println(myFreqs.get(k) + "\t" + myWords.get(k));
        // }
    }
    private int findIndexOfMax() {
        findUnique();
        int bigValue = 0;
        int bigIndex = 0;
        for (int k = 0; k < myFreqs.size(); k++) {
            if (bigValue < myFreqs.get(k)) {
                bigValue = myFreqs.get(k);
                bigIndex = k;
            }
        }
        return bigIndex;
    }
    public void testFindIndexOfMax() {
        int IndexOfMax = findIndexOfMax();
        System.out.println("The word that occurs most often and its count are: " + 
        myWords.get(IndexOfMax) + " " +
        myFreqs.get(IndexOfMax));
    }
}
