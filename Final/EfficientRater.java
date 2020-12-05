import java.util.*;

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String, Rating>();
    }
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item,rating));
    }
    public boolean hasRating(String item) {
        return myRatings.containsKey(item) == true;
    }
    public String getID() {
        return myID;
    }
    public int length() {
        return myID.length();
    }
    @Override
    public boolean equals(Object o) {
        EfficientRater other = (EfficientRater) o;
        if (this.length() != other.length()) {
            return false;
        }
        if (! this.getID().equals(other.getID())) {
            return false;
        }
        return true;
    }
    public double getRating(String item) {
        if (hasRating(item) == true) {
            return myRatings.get(item).getValue();
        }
        else {
            return -1;
        }
    }
    public int numRatings() {
        return myRatings.size();
    }
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (Rating r : myRatings.values()) {
            list.add(r.getItem());
        }
        return list;
    }
}
/** You will make several changes to this class, including:
 * - Change the ArrayList of type Rating private variable to a HashMap<String,Rating>. The key in the HashMap
 * is a movie ID, and its value is a rating associated with this movie.
 * - You will need to change addRating to instead add a new Rating to the HashMap with the value associated
 * with the movie ID String item as the key in the HashMap.
 * - What other changes need to be made?
 *
 */
