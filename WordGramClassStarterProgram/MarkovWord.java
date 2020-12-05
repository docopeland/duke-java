
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    public MarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myText = new String[myOrder];
    }
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, numWords);
        sb.append(kGram);
        sb.append(" ");
        for(int k=0; k < numWords-myOrder; k++){
            ArrayList<String> follows = getFollows(kGram);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            kGram = kGram.shiftAdd(next);
            
        }
        return sb.toString().trim();
    }
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while (start < myText.length - 1) {
            int indx = indexOf(myText, kGram, start);
            if (indx == -1) {
                break;
            }
            if (indx + 1 >= myText.length - 1) {
                break;
            }
            int next = indx + 1;
            follows.add(myText[next]);
            start = indx + 1;
        }
        return follows;
    }
    private int indexOf(String[] words, WordGram target, int start) {
        int answer = -1;
        for (int k = start; k < words.length; k++) {
            if (words[k].equals(target) && answer != -1) {
                answer = k;
            }
        }
        return answer;
    }
}
