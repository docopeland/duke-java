
/**
 * Write a description of Tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
public class Tester {
    public void testGetFollows() {
        MarkovOne mark = new MarkovOne();
        String text = "this is a test yes this is a test.";
        mark.setTraining(text);
        ArrayList<String> follows = mark.getFollows("o");
        System.out.println(follows + "\n" + "size: " + follows.size());
    }
    public void testGetFollowsWithFile() {
        FileResource fr = new FileResource();
        String text = fr.asString();
        text = text.replace('\n', ' ');
        MarkovOne mark = new MarkovOne();
        mark.setTraining(text);
        ArrayList<String> follows = mark.getFollows("o");
        System.out.println(follows + "\n" + "size: " + follows.size());
    }
}
