
/**
 * Testing1 checks if there are two occurrences of a first string in a second
 * string, returns a boolean. Testing2 returns the string that occurs AFTER
 * the first occurrence of a string in a second string; if no occurrences, 
 * returns the second string.
 * 
 * @author Danielle 
 * @version 11 July, 2020
 */
public class Part3 {
    public Boolean twoOccurrences(String stringa, String stringb) {
        // create a index for the 1st occurrance
        int index1 = stringb.indexOf(stringa);
        // create a length of stringa
        int length = stringa.length();
        // if index1 is empty; return false
        if (index1 == -1) {return false;}
        // create a index for the 2nd occurrance
        int index2 = stringb.indexOf(stringa,(index1+length));
        // if index 2 is empty' return false
        if (index2 == -1) {return false;}
        else {return true;}
    }
    public String lastPart(String stringa, String stringb) {
        // indexOf 1st occurrance of stringa
        int index1 = stringb.indexOf(stringa);
        //length of stringa
        int lengthStringA = stringa.length();
        // length of string
        int lengthTotal = stringb.length();
        // if index1 is empty; return string b
        if (index1 == -1) {return stringb;}
        // return the end of the string
        else {return stringb.substring(index1+lengthStringA);}
    }
    public void testing1() {
        System.out.println(twoOccurrences("an","banana"));
        System.out.println(twoOccurrences("l","danielle"));
        System.out.println(twoOccurrences("ar","sharon"));
        System.out.println(twoOccurrences("ff","tiffany"));
        System.out.println(twoOccurrences("f","tiffany"));
        System.out.println(twoOccurrences("d","sharon"));
        System.out.println(twoOccurrences("s","danielle"));
    }
    public void testing2() {
        System.out.println(lastPart("an","banana"));
        System.out.println(lastPart("l","danielle"));
        System.out.println(lastPart("ar","sharon"));
        System.out.println(lastPart("ff","tiffany"));
        System.out.println(lastPart("f","tiffany"));
        System.out.println(lastPart("d","sharon"));
        System.out.println(lastPart("s","danielle"));
    }
}
