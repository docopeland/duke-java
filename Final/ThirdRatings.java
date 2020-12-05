
/**
 * Write a description of SecondRatings here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.*;

public class ThirdRatings {
    private ArrayList<Rater> myRaters;
    public ThirdRatings() {
        // default constructor
        this("ratings.csv");
    }
    public ThirdRatings(String ratingsfile) {
        FirstRatings firstR = new FirstRatings();
        myRaters = firstR.loadRaters(ratingsfile);
    }
    public int getRaterSize() {

        return myRaters.size();
    }
    private double getAverageByID(String id, int minimalRaters) {
        double avg = 0;
        double ratings = 0;
        int raters = 0;
        for (int k = 0; k < myRaters.size(); k++) {
            if (myRaters.get(k).hasRating(id) == true) {
                ratings += myRaters.get(k).getRating(id);
                raters++;
            }
        }
        if (raters >= minimalRaters) {
            avg = ratings/raters;
        }
        return avg;
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (int k = 0; k < movies.size(); k++) {
            String id = movies.get(k);
            double avg = getAverageByID(id, minimalRaters);
            if (avg > 0) {
                Rating current = new Rating(id, avg);
                ratings.add(current);
            }
        }
        return ratings;
    }
    public ArrayList<Rating> getAverageRatingsByFilter (int minimalRaters, Filter filterCriteria) {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (int k = 0; k < movies.size(); k++) {
            String id = movies.get(k);
            double avg = getAverageByID(id, minimalRaters);
            if (avg > 0) {
                Rating current = new Rating(id, avg);
                ratings.add(current);
            }
        }
        return ratings;
    }
}