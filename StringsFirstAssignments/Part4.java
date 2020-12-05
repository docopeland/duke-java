
/**
 * Write a description of Part4 here.
 * 
 * @author Danielle 
 * @version 11 July, 2020
 */
import edu.duke.*;
public class Part4 {
    public void findURL() {
        // create a urlResource
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        // create a string to look for
        String tube = "youtube.com";
        // for each line of the urlResource
        for(String search : ur.lines()){
            // convert each word to lowercase
            String tempLower = search.toLowerCase();
            //create a indexOf tempLower
            int tempInd = tempLower.indexOf(tube);
            // see if any of the words contain youtube
            if (tempInd != -1) {
                // create a index for the first "
                int index1 = search.lastIndexOf("\"", tempInd);
                // create a index for the second "
                int index2 = search.indexOf("\"",index1+1);
                // the link is the substring btwn ""
                String link = search.substring(index1+1,index2);
                // print out the links
                System.out.println(link);
            }
        }
    }
}
