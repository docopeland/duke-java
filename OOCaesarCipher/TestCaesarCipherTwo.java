import edu.duke.*;
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public class TestCaesarCipherTwo {
    private int[] countLetters(String message) {
        StringBuilder sb = new StringBuilder(message);
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int [] counts = new int [26];
        for (int k = 0; k < message.length(); k++) {
            char ch = sb.charAt(k);
            char chUP = Character.toUpperCase(ch);
            int dex = abc.indexOf(chUP);
            if (dex != -1) {
                counts[dex]++;
            }
        }
        return counts;
    }
    private int maxIndex(int [] values) {
        int biggestNo = 0;
        int biggestK = 0;
        for (int k = 0; k < values.length; k++) {
            int current = values[k];
            if (current > biggestNo) {
                biggestNo = current;
                biggestK = k;
            }
        }
        return biggestK;
    }
    private String halfOfString(String message, int start) {
        StringBuilder str = new StringBuilder(message);
        StringBuilder sb = new StringBuilder();
        for (int k = start; k < message.length(); k = k + 2) {
            char ch = str.charAt(k);
            sb.append(ch);
        }
        return sb.toString();
    }
    private int getKey(String s) {
        int [] count = countLetters(s);
        int max = maxIndex(count);
        int key = max - 4;
        if (max < 4) {
            key = 4 - max;
        }
        return key;
    }
    private String breakCaesarCipher(String input) {
        String oddString = halfOfString(input, 0);
        String evenString = halfOfString(input,1);
        int key1 = getKey(oddString);
        int key2 = getKey(evenString);
        System.out.println("Key 1: " + key1 + " and Key 2: " + key2);
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26-key1,26-key2);
        String broken = cc2.encrypt(input);
        return broken;
    }
    public void simpleTest2() {
       FileResource fr = new FileResource();
       String message = fr.asString();
       System.out.println(message);
       CaesarCipherTwo cc2 = new CaesarCipherTwo(21,8);
       String encrypted = cc2.encrypt(message);
       System.out.println(encrypted);
       String decrypted = cc2.decrypt(encrypted);
       System.out.println(decrypted);
       //String decrypted = breakCaesarCipher(encrypted);
       //System.out.println(decrypted);
    }
    public void quiz() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        String decrypted = breakCaesarCipher(message);
        System.out.println(decrypted);
    }
}
