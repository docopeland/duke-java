
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovFour {
    private String myText;
    private Random myRandom;
    public MarkovFour() {
        myRandom = new Random();
    }
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    public void setTraining(String s){
        myText = s.trim();
    }
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-4);
        String key = myText.substring(index, index+4);
        sb.append(key);
        for(int k=0; k < numChars-4; k++){
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
    public ArrayList<String> getFollows(String key) {
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
}

