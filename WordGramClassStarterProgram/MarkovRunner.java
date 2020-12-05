
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st);  
        } 
    } 
    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 
    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st, 100, 844);
    } 
    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 
    public void testHashMap() {
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        EfficientMarkovWord EffMark = new EfficientMarkovWord(2);
        //String st = "this is a test yes this is really a test yes a test this is wow";
        runModel(EffMark, st, 50, 65);
        EffMark.printHashMapInfo();
    }
    public void compareMethods() {
        //MarkovWord
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        double timeA = System.nanoTime(); 
        MarkovWord markovWord = new MarkovWord(2);
        runModel(markovWord, st, 100, 42);
        double timeB = System.nanoTime();
        //EfficientMarkovWord
        double timeC = System.nanoTime();
        EfficientMarkovWord EffMark = new EfficientMarkovWord(2);
        runModel(EffMark, st, 100, 42);
        double timeD = System.nanoTime();
        System.out.println("MarkovModel time: " + (timeB - timeA));
        System.out.println("EfficientMarkovModel time: " + (timeD - timeC));
        System.out.println("Delta: " + ((timeB - timeA) - (timeD - timeC)));
    }
    /** Specifically, you should:
     * -In the MarkovRunner class, create a void method named compareMethods that runs a
     * MarkovWord and an EfficientMarkovWord. In particular,
     * --- Make both order-2 Markov models
     * --- Use seed 42 and set the length of text to generate to be 100
     * --- Both should call runModel that generates random text three times for each.
     * -Run the MarkovWord first and then the EfficientMarkovWord with the file 
     * “hawthorne.txt” as the training text. One of them should be noticeably faster. You 
     * can calculate the time each takes by using System.nanoTime() for the current time. */
}
