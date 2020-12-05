
/**
 * Write a description of MarkovFour here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel {
    HashMap<String,ArrayList<String>> textMap;
    public EfficientMarkovModel(int key) {
        myRandom = new Random();
        keyNum = key;
        textMap = new HashMap<String,ArrayList<String>>();
    }
    public void setTraining(String s){
        myText = s.trim();
        buildMap();
    }
    @Override
    public String toString() {
        return "EfficientMarkovModel order of " + keyNum;
    }
    @Override
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-keyNum);
        String key = myText.substring(index, index+keyNum);
        sb.append(key);
        for(int k=0; k < numChars-keyNum; k++){
            ArrayList<String> follows = getFollows(key);
            if (!(follows == null)) {
                index = myRandom.nextInt(follows.size());
                String next = follows.get(index);
                sb.append(next);
                key = key.substring(1) + next;
            }
        }
        return sb.toString();
    }
    public void buildMap() {
        int pos = 0;
        for (int k = 0; k < myText.length() - keyNum; k++) {
            int end = k + keyNum;
            String mapKey = myText.substring(k, end);
            ArrayList<String> follows = new ArrayList<String>();
            if ((k + keyNum) > myText.length()) {
                textMap.put(mapKey, follows);
            }
            if (textMap.containsKey(mapKey)) {
                follows = textMap.get(mapKey);
                follows.add(myText.substring(end, end+1));
                textMap.put(mapKey, follows);
            }
            if (!textMap.containsKey(mapKey)) {
                follows.add(myText.substring(end, end+1));
                textMap.put(mapKey, follows); 
            }
        }
    }
    @Override
    public ArrayList<String> getFollows(String key) {
        ArrayList<String> answer = new ArrayList<String>();
        answer = textMap.get(key);
        return answer;
    }
    public void printHashMapInfo() {
        //System.out.println(textMap);
        System.out.println("number of keys: " + textMap.size());
        int largest = 0;
        ArrayList<String> large = new ArrayList<String>();
        for (String s : textMap.keySet()) {
            if (textMap.get(s).size() > largest) {
                large.clear();
                largest = textMap.get(s).size();
                large.add(s);
            }
            if (textMap.get(s).size() == largest) {
                if (!large.contains(s)) {
                    large.add(s);
                }
            }
        }
        System.out.println(largest + ": " + large);
    }
}

