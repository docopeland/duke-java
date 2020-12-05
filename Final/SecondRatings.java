
/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    public SecondRatings(String moviefile, String ratingsfile) {
        FirstRatings firstR = new FirstRatings();
        myMovies = firstR.loadMovie(moviefile);
        myRaters = firstR.loadRaters(ratingsfile);
    }
    public int getMovieSize() {
        return myMovies.size();
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
        for (int k = 0; k < myMovies.size(); k++) {
            String id = myMovies.get(k).getID();
            double avg = getAverageByID(id, minimalRaters);
            if (avg > 0) {
                Rating current = new Rating(id, avg);
                ratings.add(current);
            }
        }
        return ratings;
    }
    public String getTitle(String id) {
        String name = "ID NOT FOUND";
        for (int k = 0; k < myMovies.size(); k++) {
            String currentId = myMovies.get(k).getID();
            if (currentId.equals(id)) {
                name = myMovies.get(k).getTitle();
            }
        }
        return name;
    }
    public String getID(String title) {
        String idNo = "NO SUCH TITLE";
        for (int k = 0; k < myMovies.size(); k++) {
            String currentTitle = myMovies.get(k).getTitle();
            if (currentTitle.equals(title)) {
                idNo = myMovies.get(k).getID();
            }
        }
        return idNo;
    }
}