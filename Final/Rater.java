import java.util.ArrayList;

public interface Rater {
    public void addRating(String item, double rating);
    public boolean hasRating(String item);
    public String getID();
    public int length();
    @Override
    public boolean equals(Object o);
    public double getRating(String item);
    public int numRatings();
    public ArrayList<String> getItemsRated();
}
