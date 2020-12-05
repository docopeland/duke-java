
/**
 * finds out how many time stringa shows up in string b. 
 * 
 * @author Danielle
 * @version 12 July, 2020
 */
public class Part2 {
    public int howMany(String stringa, String stringb) {
        String strIn = stringa;
        String strOut = stringb;
        int numb = 0;
        int index = 0;
        while (index <= strOut.length()) {
            index = strOut.indexOf(strIn,index);
            if (index != -1) {
                index = index + strIn.length();
                numb = numb+1;
            }
            else {
                break;
            }
        }
        return numb;
    }
    public void testHowMany() {
        System.out.println(howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println(howMany("AA","ATAAAA"));
        System.out.println(howMany("f","tiffany"));
        System.out.println(howMany("ff","tiffany"));
        System.out.println(howMany("l","danielle"));
        System.out.println(howMany("ll","danielle"));
        System.out.println(howMany("e","danielle"));
        System.out.println(howMany("ee","danielle"));
    }
}