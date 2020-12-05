
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    protected int keyNum;
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    @Override
    public String toString() {
        return "MarkovModel of order " + keyNum;
    }
    public void setTraining(String s) {
        myText = s.trim();
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    protected ArrayList<String> getFollows(String key) {
        ArrayList<String> answer = new ArrayList<String>();
        int pos = 0;
        while (pos < myText.length() - 1) {
            int indx = myText.indexOf(key, pos);
            if (indx == -1) {
                break;
            }
            if (indx + key.length() > myText.length() - 1) {
                break;
            }
            int length = indx + key.length();
            answer.add(myText.substring(length,length+1));
            pos = indx + key.length();
        }
        return answer;
    }
    abstract public String getRandomText(int numChars);
}
