
/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarBreaker {
    public int[] countLetters(String message) {
        StringBuilder sb = new StringBuilder(message);
        String abc = "abcdefghijklmnopqrstuvwxyz";
        int [] counts = new int [26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(sb.charAt(k));
            int dex = abc.indexOf(ch);
            if (dex != -1) {
                counts[dex]++;
            }
        }
        return counts;
    }
    public int maxIndex(int [] values) {
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
    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        int [] freq = countLetters(encrypted);
        int max = maxIndex(freq);
        int dKey = max - 4;
        if (max < 4) {
            dKey = 4 - max;
        }
        return (cc.encrypt(encrypted, 26-dKey));
    }
    public void testDecrypt1Key() {
        FileResource fr = new FileResource(); 
        String encrypted = fr.asString();
        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }
    public String halfOfString(String message, int start) {
        StringBuilder str = new StringBuilder(message);
        StringBuilder sb = new StringBuilder();
        for (int k = start; k < message.length(); k = k + 2) {
            char ch = str.charAt(k);
            sb.append(ch);
        }
        return sb.toString();
    }
    public int getKey(String s) {
        int [] count = countLetters(s);
        int max = maxIndex(count);
        int key = max - 4;
        if (max < 4) {
            key = 4 - max;
        }
        return key;
    }
    public String decryptTwoKeys(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        String oddChar = halfOfString(encrypted, 0);
        String evenChar = halfOfString(encrypted, 1);
        int oddKey = getKey(oddChar);
        int evenKey = getKey(evenChar);
        System.out.println(oddKey + " " + evenKey);
        String oddDec = cc.encrypt(oddChar, 26-oddKey);
        String evenDec = cc.encrypt(evenChar, 26-evenKey);
        System.out.println(oddDec + " " + evenDec);
        StringBuilder message = new StringBuilder();
        int odd = 0;
        int even = 0;
        for (int k = 0; k < encrypted.length(); k++) {
            if (k % 2 == 0) {
                char oddCh = oddDec.charAt(odd);
                message.append(oddCh);
                odd++;
            }
            if (k % 2 == 1) {
                char evenCh = evenDec.charAt(even);
                message.append(evenCh);
                even++;
            }
        }
        return message.toString();
    }
    public void test() {
        //FileResource fr = new FileResource();
        String message = "Geometric computing research at Duke works under the common rationalization of the field of computational geometry, often given in the past, that the world around us is three-dimensional and questions how things in this world relate to each other are inherently geometric. Take moving a piano through a door-frame and planning a flight-path that avoids collisions with other airplanes as two examples. It should therefore not surprise that compu";
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encryptTwoKeys(message, 17, 4);
        System.out.println(encrypted);
        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);
    }
}
