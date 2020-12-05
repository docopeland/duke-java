
/**
 * Write a description of GladLibMap here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.util.*;
import java.io.*;

public class GladLibMap {
    private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> prevSub;
    private ArrayList<String> labelsUsed;
    private Random myRandom;
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    public GladLibMap(){
    	myMap = new HashMap<String,ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
    	myRandom = new Random();
    	prevSub = new ArrayList<String>();
    	labelsUsed = new ArrayList<String>();
    }
    public GladLibMap(String source){
    	myMap = new HashMap<String,ArrayList<String>>();
    	initializeFromSource(source);
    	myRandom = new Random();
    	prevSub = new ArrayList<String>();
    	labelsUsed = new ArrayList<String>();
    }
    private void initializeFromSource(String source) {
    	String[] cats = {"adjective","noun","color","country","name","animal",
    	    "timeframe","verb","fruit"};
    	for (int k = 0; k < cats.length; k++) {
    	   String category = cats[k];
    	   String sourced = source + "/" + category + ".txt";
    	   ArrayList<String> list = new ArrayList<String>();
    	   list = readIt(sourced);
    	   myMap.put(category,list);
        }
    }
    private String randomFrom(ArrayList<String> source){
    	int index = myRandom.nextInt(source.size());
    	return source.get(index);
    }
    private String getSubstitute(String label) {
    	for (String s : myMap.keySet()) {
    	    if (label.equals(s)) {
    	        return randomFrom(myMap.get(s));
    	    }
    	}
    	if (label.equals("number")){
    		return ""+myRandom.nextInt(50)+5;
    	}
    	return "**UNKNOWN**";
    }
    private String processWord(String w){
    	int first = w.indexOf("<");
    	int last = w.indexOf(">",first);
    	if (first == -1 || last == -1){
    		return w;
    	}
    	String prefix = w.substring(0,first);
    	String suffix = w.substring(last+1);
    	if (! labelsUsed.contains(w.substring(first+1,last))) {
    	    labelsUsed.add(w.substring(first+1,last));
    	}
    	String sub = getSubstitute(w.substring(first+1,last));
    	if (! prevSub.contains(sub)) {
    	    prevSub.add(sub);
    	}
    	else {
    	    sub = processWord(w);
    	  }
    	return prefix+sub+suffix;
    }
    private void printOut(String s, int lineWidth){
    	int charsWritten = 0;
    	for(String w : s.split("\\s+")){
    		if (charsWritten + w.length() > lineWidth){
    			System.out.println();
    			charsWritten = 0;
    		}
    		System.out.print(w+" ");
    		charsWritten += w.length() + 1;
    	}
    }
    private String fromTemplate(String source){
    	String story = "";
    	if (source.startsWith("http")) {
    		URLResource resource = new URLResource(source);
    		for(String word : resource.words()){
    			story = story + processWord(word) + " ";
    		}
    	}
    	else {
    		FileResource resource = new FileResource(source);
    		for(String word : resource.words()){
    			story = story + processWord(word) + " ";
    		}
    	}
    	return story;
    }
    private ArrayList<String> readIt(String source){
    	ArrayList<String> list = new ArrayList<String>();
    	if (source.startsWith("http")) {
    		URLResource resource = new URLResource(source);
    		for(String line : resource.lines()){
    			list.add(line);
    		}
    	}
    	else {
    		FileResource resource = new FileResource(source);
    		for(String line : resource.lines()){
    			list.add(line);
    		}
    	}
    	return list;
    }
    private int totalWordsInMap() {
        int totalWords = 0;
        for (String s : myMap.keySet()) {
            totalWords = totalWords + myMap.get(s).size();
        }
        return totalWords;
    }
    private int totalWordsConsidered() {
        int totalWords = 0;
        for (String s : labelsUsed) {
            if (! s.equals("number")) {
                totalWords = totalWords + myMap.get(s).size();
            }
        }
        return totalWords;
    }
    public void makeStory() {
        labelsUsed.clear();
        prevSub.clear();
    	String story = fromTemplate("data/madtemplate2.txt");
    	printOut(story, 50);
    	System.out.println("\n");
    	int totalWords = totalWordsInMap();
    	System.out.println("The total number of words to pick from: " + totalWords);
    	System.out.println(prevSub);
    	int totalWordsConsidered = totalWordsConsidered();
    	System.out.println("The possible words to replace: " + totalWordsConsidered);
    }
}
