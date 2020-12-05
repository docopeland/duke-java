
/**
 * Write a description of WordsInFiles here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;
public class WordsInFiles {
    private HashMap<String,ArrayList<String>> wordMap;
    public WordsInFiles() {
        wordMap = new HashMap<String,ArrayList<String>>();
    }
    private void addWordsFromFile(File f) {
        FileResource fr = new FileResource(f);
        String fName = f.getName();
        for (String s : fr.words()) {
            ArrayList<String> files = new ArrayList<String>();
            if (wordMap.containsKey(s)) {
                files.clear();
                files = wordMap.get(s);
                if (! files.contains(fName)) { 
                    files.add(fName);
                    wordMap.put(s,files);
                }
            }
            else {
                files.clear();
                files.add(fName);
                wordMap.put(s,files);
            }
        }
    }
    private void buildWordFileMap() {
        wordMap.clear();
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            addWordsFromFile(f);
        }
    }
    private int maxNumber() {
        int max = 0;
        for (String s : wordMap.keySet()) {
            ArrayList<String> files = new ArrayList<String>();
            files = wordMap.get(s);
            int current = files.size();
            if (current > max) {
                max = current;
            }
        }
        return max;
    }
    private ArrayList<String> wordsInNumFiles(int number) {
        ArrayList<String> words = new ArrayList<String>();
        for (String s : wordMap.keySet()) {
            ArrayList<String> files = new ArrayList<String>(); 
            files = wordMap.get(s);
            int current = files.size();
            if (number == current) {
                words.add(s);
            }
        }
        return words;
    }
    private void printFilesIn(String word) {
        ArrayList<String> files = new ArrayList<String>();
        files = wordMap.get(word);
        for (int k = 0; k < files.size(); k++) {
            System.out.println(files.get(k));
        }
    }
    public void tester() {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("The max num of files any word is in is: " + max);
        ArrayList<String> words = wordsInNumFiles(4);
        int maxxer = words.size();
        System.out.println("the words that are in a exact number of files " 
        + maxxer);
        printFilesIn("tree");
        
        //for (String s : words) {
            //System.out.println(s);
            //printFilesIn(s);
        //}
    }
}
