import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliced = new StringBuilder();
        for (int k = whichSlice; k < message.length(); k = k + totalSlices) {
            char ch = message.charAt(k);
            sliced.append(ch);
        }
        return sliced.toString();
    }
    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        for (int k = 0; k < klength; k++) {
            CaesarCracker cc = new CaesarCracker(mostCommon);
            ArrayList<String> slices = new ArrayList<String>();
            String slice = sliceString(encrypted, k, klength);
            int keyI = cc.getKey(slice);
            key[k] = keyI;
        }
        return key;
    }
    public HashSet<String> readDictionary(FileResource fr) {
        HashSet<String> hash = new HashSet<String>();
        for (String s : fr.lines()) {
            s = s.toLowerCase();
            hash.add(s);
        }
        return hash;
    }
    public int countWords(String message, HashSet<String> dictionary) {
        int count = 0;
        message = message.toLowerCase();
        String[] split = message.split("\\W+");
        for (int k = 0; k < split.length; k++) {
            if (dictionary.contains(split[k])) {
                count++;
            }
        }
        return count;
    }
    public char mostCommonCharIn(HashSet<String> dictionary) {
        char mostCommon = '\0';
        int count = 0;
        HashMap<Character,Integer> letters = new HashMap<Character,Integer>();
        for (String s: dictionary) {
            StringBuilder word = new StringBuilder(s);
            for (int k = 0; k < word.length(); k++) {
                char chTemp = word.charAt(k);
                char ch = '\0';
                if(Character.isLetter(chTemp)) {
                    ch = word.charAt(k);
                }
                if (letters.containsKey(ch)) {
                    letters.put(ch,letters.get(ch)+1);
                }
                else {
                    letters.put(ch,1);
                }
            }
        }
        for (char ch : letters.keySet()) {
            int current = letters.get(ch);
            if (current > count) {
                count = current;
                mostCommon = ch;
            }
        }
        return mostCommon;
    }
    public int breakForLanguage(String encrypted, HashSet<String> dictionary) {
        int key = 0;
        int mostWords = 0;
        for (int k = 1; k <= 100; k++) {
            int[] keys = tryKeyLength(encrypted, k, 'e');
            VigenereCipher vc = new VigenereCipher(keys);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (count > mostWords) {
                mostWords = count;
                key = k;
            }
        }
        return key;
    }
    public void breakForAllLangs(String encrypted,HashMap<String,HashSet<String>> languages){
        int mostWords = 0;
        String goodLang = "";
        for (String s : languages.keySet()) {
            HashSet<String> dictionary = languages.get(s);
            char mostCommon = mostCommonCharIn(dictionary);
            int keylength = breakForLanguage(encrypted, dictionary);
            int [] key = tryKeyLength(encrypted, keylength, mostCommon);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int count = countWords(decrypted, dictionary);
            if (count > mostWords) {
                mostWords = count;
                goodLang = s;
            }
        }
        System.out.println(goodLang);
        for (String s : languages.keySet()) {
            if (goodLang.equals(s)) {
                HashSet<String> dictionary = languages.get(s);
                char mostCommon = mostCommonCharIn(dictionary);
                int keylength = breakForLanguage(encrypted, dictionary);
                int [] key = tryKeyLength(encrypted, keylength, mostCommon);
                VigenereCipher vc = new VigenereCipher(key);
                String decrypted = vc.decrypt(encrypted);
                System.out.println(decrypted);
            }
        }
    }
    
    {
    /** Specifically, you should do the following:
     * --In the VigenereBreaker class, write the public method breakForAllLangs, which has
     * two parameters—a String encrypted, and a HashMap, called languages, mapping a String
     * representing the name of a language to a HashSet of Strings containing the words in
     * that language. Try breaking the encryption for each language, and see which gives
     * the best results! Remember that you can iterate over the languages.keySet() to get
     * the name of each language, and then you can use .get() to look up the corresponding
     * dictionary for that language. You will want to use the breakForLanguage and
     * countWords methods that you already wrote to do most of the work (it is slightly
     * inefficient to re-count the words here, but it is simpler, and the inefficiency is
     * not significant). You will want to print out the decrypted message as well as the
     * language that you identified for the message. */
    }
    
    public void breakVigenere () {
        FileResource fr = new FileResource();
        String encrypted = fr.asString();
        DirectoryResource dr = new DirectoryResource();
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        for (File f : dr.selectedFiles()) {
            FileResource dictionary = new FileResource(f);
            HashSet<String> diction = readDictionary(dictionary);
            String lang = f.getName();
            languages.put(lang, diction);
        }
        breakForAllLangs(encrypted, languages);
    }
}
