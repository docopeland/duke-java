
/**
 * Write a description of TestCaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipher {
    private int[] countLetters(String message) {
        StringBuilder sb = new StringBuilder(message);
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int [] counts = new int [26];
        for (int k = 0; k < message.length(); k++) {
            char ch = sb.charAt(k);
            int dex = abc.indexOf(ch);
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
    private String breakCaesarCipher(String input) {
        int [] count = countLetters(input);
        int max = maxIndex(count);
        int dKey = max - 4;
        if (max < 4) {
            dKey = 4 - max;
        }
        CaesarCipher cc = new CaesarCipher(26 - dKey);
        String broken = cc.encrypt(input);
        return broken;
    }
    public void simpleTest() {
       FileResource fr = new FileResource();
       String message = fr.asString();
       System.out.println(message);
       CaesarCipher cc = new CaesarCipher(15);
       String encrypted = cc.encrypt(message);
       System.out.println(encrypted);
       //String decrypted = cc.decrypt(encrypted);
       //System.out.println(decrypted);
       String decrypted = breakCaesarCipher(encrypted);
       System.out.println(decrypted);
    }
}
