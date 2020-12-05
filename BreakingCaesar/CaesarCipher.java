
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        StringBuilder msg = new StringBuilder(input);
        String abcOrUp = new String("ABCDEFGHIJKLMNOPQRSTUVWXYZ"); 
        String abcEnUp = abcOrUp.substring(key) + abcOrUp.substring(0,key);
        String abcOrLo = new String("abcdefghijklmnopqrstuvwxyz");
        String abcEnLo = abcOrLo.substring(key) + abcOrLo.substring(0,key);
        for (int i = 0; i < input.length(); i++) {
            char ch = msg.charAt(i);
            if (Character.isUpperCase(ch)) {
                int iIndex = abcOrUp.indexOf(ch);
                if (iIndex != -1) {
                    char co = abcEnUp.charAt(iIndex);
                    msg.setCharAt(i, co);
                }
            }
            if (Character.isLowerCase(ch)) {
                int iIndex = abcOrLo.indexOf(ch);
                if (iIndex != -1) {
                    char co = abcEnLo.charAt(iIndex);
                    msg.setCharAt(i, co);
                }
            }
        }
        return msg.toString();
    }
    public void testCaesar() {
        FileResource fr = new FileResource();
        String message = fr.asString();
        int key = 15;
        String encrypted = encrypt(message, key);
        System.out.println(message);
        System.out.println("key is " + key + "\n" + encrypted);
    }
    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String letter = input.substring(i,i+1);
            if (i % 2 == 0) {
                sb.append(encrypt(letter, key1));
            }
            else {
                sb.append(encrypt(letter, key2));
            }
        }
        return sb.toString();
    }
    public void testTwoKeys() {
        FileResource fr = new FileResource();
        String input = fr.asString();
        int key1 = 8;
        int key2 = 21;
        String encrypt = encryptTwoKeys(input, key1, key2);
        System.out.println(input + "\n" + encrypt);
    }
}
