
/**
 * Write a description of WordPlay here.
 * 
 * @author (danielle) 
 * @version (20/7/20)
 */

import edu.duke.*;
import java.util.*;;

public class WordPlay {
    public Boolean isVowel(char ch){
        Boolean vowel = false;
        if (Character.isLetter(ch)) {
            if (Character.isUpperCase(ch)) {
                if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' ||
                   ch == 'U') {
                       vowel = true;
                    }
            }
            else {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' ||
                   ch == 'u') {
                    vowel = true;
                }
            }
        }
        return vowel;
    }
    public void testIsVowel() {
        char ch = '!';
        System.out.print(ch + " ");
        System.out.println(isVowel(ch));
    }
    public String replaceVowels(String phrase, char ch) {
        StringBuilder noVowels = new StringBuilder(phrase);
        for (int i = 0; i < noVowels.length(); i++) {
            char current = noVowels.charAt(i);
            if (isVowel(current) == true) {
                noVowels.setCharAt(i, ch);
            }
            else {}
        }
        return noVowels.toString();
    }
    public void testReplaceVowels() {
        String phrase = "Hello World";
        char ch = '*';
        System.out.println(replaceVowels(phrase, ch));
    }
    public String emphasize(String phrase, char ch) {
        StringBuilder funny = new StringBuilder(phrase);
        for (int i = 0; i < funny.length(); i++) {
            char current = funny.charAt(i);
            if (Character.toUpperCase(current) == ch || 
            Character.toLowerCase(current) == ch) {
                // odd number
                if (i % 2 == 0) {
                    funny.setCharAt(i, '*');
                }
                // even number
                if (i % 2 == 1) {
                    funny.setCharAt(i, '+');
                }
            }
        }
        return funny.toString();
    }
    public void testEmphasize() {
        String phrase = "dnA ctgaaactga";
        char ch = 'a';
        System.out.println(emphasize(phrase, ch));
    }
}