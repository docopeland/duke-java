
/**
 * Write a description of CharactersInPlay here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*; 
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String> charNames;
    private ArrayList<Integer> charLines;
    public CharactersInPlay() {
        charNames = new ArrayList<String>();
        charLines = new ArrayList<Integer>();
    }
    private void update(String person) {
        if (! charNames.contains(person)) {
            charNames.add(person);
            charLines.add(1);
        }
        else {
            int index = charNames.indexOf(person);
            int value = charLines.get(index) + 1;
            charLines.set(index, value);
        }
    }
    private void findAllCharacters() {
        charNames.clear();
        charLines.clear();
        FileResource fr = new FileResource();
        for (String line : fr.lines()) {
            int period = line.indexOf(".");
            if (period != -1) {
                String person = line.substring(0,period);
                update(person);
            }
        }
    }
    public void testerCharacters() {
        findAllCharacters();
        for (int k = 0; k < charNames.size(); k++) {
            System.out.println(charNames.get(k) + "\t" + charLines.get(k));
        }
    }
    private void charactersWithNumParts(int num1, int num2) {
        findAllCharacters();
        for (int k = 0; k < charNames.size(); k++) {
            if(num1 < charLines.get(k) && charLines.get(k) < num2) {
                System.out.println(charNames.get(k) + "\t" + charLines.get(k));
            }
        }
    }
    public void testMains() {
        charactersWithNumParts(10,15);
    }
}
