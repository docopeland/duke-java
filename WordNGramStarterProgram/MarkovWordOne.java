
/**
 * Write a description of class MarkovWordOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class MarkovWordOne implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    public MarkovWordOne() {
        myRandom = new Random();
    }
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    public void setTraining(String text){
        myText = text.split("\\s+");
    }
    public String getRandomText(int numWords){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length-1);  // random word to start with
        String key = myText[index];
        sb.append(key);
        sb.append(" ");
        for(int k=0; k < numWords-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            sb.append(" ");
            key = next;
        }
        return sb.toString().trim();
    }
    private ArrayList<String> getFollows(String key) {
        ArrayList<String> follows = new ArrayList<String>();
        int start = 0;
        while (start < myText.length - 1) {
            int indx = indexOf(myText, key, start);
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
    private int indexOf(String[] words, String target, int start) {
        int answer = -1;
        for (int k = start; k < words.length; k++) {
            if (words[k].equals(target) && answer == -1) {
                answer = k;
            }
        }
        return answer;
    }
    public void testIndexOf() {
        String text = "this is just a test yes this is a simple test";
        String [] st = text.split(" ");
        System.out.println("this 0 " + indexOf(st, "this", 0));
        System.out.println("frog 0 " + indexOf(st, "frog", 0));
        System.out.println("frog 5 " + indexOf(st, "frog", 5));
        System.out.println("simple 2 " + indexOf(st, "simple", 2));
        System.out.println("test 2 " + indexOf(st, "test", 0));
        System.out.println("test 5 " + indexOf(st, "test", 5));
    }
}
