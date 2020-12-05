
/**
 * Write a description of CaesarCipher here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipher {
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    public CaesarCipher(int mainKey) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(mainKey) + alphabet.substring(0, mainKey);
        this.mainKey = mainKey;
    }
    public String encrypt(String input) {
        StringBuilder msg = new StringBuilder(input);
        for (int i = 0; i < input.length(); i++) {
            char ch = msg.charAt(i);
            int chIndex = alphabet.indexOf(Character.toUpperCase(ch));
            if (chIndex != -1) {
                char co = shiftedAlphabet.charAt(chIndex);
                msg.setCharAt(i, co);
            }
        }
        return msg.toString();
    }
    public String decrypt(String input) {
        CaesarCipher cc = new CaesarCipher(26 - mainKey);
        return cc.encrypt(input);
    }
}
