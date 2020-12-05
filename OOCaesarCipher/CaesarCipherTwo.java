
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;
    public CaesarCipherTwo(int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        this.key1 = key1;
        this.key2 = key2;
    }
    public String encrypt(String input) {
        CaesarCipher cc1 = new CaesarCipher(key1);
        CaesarCipher cc2 = new CaesarCipher(key2);
        String encrypt1 = cc1.encrypt(input);
        String encrypt2 = cc2.encrypt(input);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String letter = input.substring(i,i+1);
            if (i % 2 == 0) {
                char oddChar = encrypt1.charAt(i);
                sb.append(oddChar);
            }
            else {
                char evenChar = encrypt2.charAt(i);
                sb.append(evenChar);
            }
        }
        return sb.toString();
    }
    public String decrypt(String input) {
        CaesarCipherTwo cc2 = new CaesarCipherTwo(26 - key1, 26-key2);
        return cc2.encrypt(input);
    }
}
