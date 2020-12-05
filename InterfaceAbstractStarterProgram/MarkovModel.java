
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovModel extends AbstractMarkovModel {
    public MarkovModel(int key) {
        myRandom = new Random();
        keyNum = key;
    }
    public void setTraining(String s){
        myText = s.trim();
    }
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-keyNum);
        String key = myText.substring(index, index+keyNum);
        sb.append(key);
        for(int k=0; k < numChars-keyNum; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = key.substring(1) + next;
        }
        return sb.toString();
    }
}

