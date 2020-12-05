
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap<WordGram,ArrayList<String>> map;
    public EfficientMarkovWord(int order) {
        myRandom = new Random();
        myOrder = order;
        myText = new String[myOrder];
        map = new HashMap<WordGram,ArrayList<String>>();
    }
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    public void setTraining(String text){
        myText = text.split("\\s+");
        buildMap();
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        WordGram kGram = new WordGram(myText, index, myOrder);
        sb.append(kGram);
        sb.append(" ");
        for(int k=0; k < numWords - myOrder - 1; k++){
            ArrayList<String> follows = getFollows(kGram);
            if ((follows.size() > 0)) {
                index = myRandom.nextInt(follows.size());
                String next = follows.get(index);
                sb.append(next);
                sb.append(" ");
                kGram = kGram.shiftAdd(next);
            }
            else {
                break;
            }
        }
        return sb.toString().trim();
    }
    private ArrayList<String> getFollows(WordGram kGram) {
        ArrayList<String> follows = map.get(kGram);
        return follows;
    }
    private int indexOf(String[] words, WordGram target, int start) {
        int answer = -1;
        for (int k = start; k < words.length - myOrder - 1; k++) {
            WordGram currentWG = new WordGram(words, k, target.length());
            if (currentWG.equals(target)) {
                answer = k;
            }
        }
        return answer;
    }
    private void buildMap() {
        for (int k = 0; k < myText.length - myOrder + 1; k++) {
            WordGram wg = new WordGram(myText, k, myOrder);
            ArrayList<String> list = new ArrayList<String>();
            if ((k + myOrder) >= myText.length) {
                map.put(wg, list);
            }
            else {
                String next = myText[k+myOrder];
                if (map.containsKey(wg)) {
                    map.get(wg).add(next);
                }
                else {
                    list.add(next);
                    map.put(wg, list);
                }
            }
        }
    }
    public void printHashMapInfo() {
        int largest = 0;
        ArrayList<WordGram> large = new ArrayList<WordGram>();
        for (WordGram wg : map.keySet()) {
            if (map.get(wg).size() > largest) {
                large.clear();
                large.add(wg);
                largest = map.get(wg).size();
            }
            if (map.get(wg).size() == largest) {
                large.add(wg);
            }
        }
        System.out.println("size: " + map.size());
        System.out.println("largest ArrayList is " + largest);
        System.out.println("keys that have max largest are " + large);
    }
}
