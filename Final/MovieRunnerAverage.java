
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class MovieRunnerAverage {
    public void printAverageRatings() {
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        SecondRatings secRat = new SecondRatings(moviefile, ratingsfile);
        System.out.println("number of movies: " + secRat.getMovieSize());
        System.out.println("number of raters: " + secRat.getRaterSize());
        System.out.println("avg ratings over 50: " );
        ArrayList<Rating> avgRatings = secRat.getAverageRatings(50);
        for (int i = 0; i < avgRatings.size() - 1; i++) {
            for (int j = 0; j < avgRatings.size() - i - 1; j++) {
                if (avgRatings.get(j).compareTo(avgRatings.get(j+1)) == 1) {
                    Rating temp = avgRatings.get(j);
                    avgRatings.set(j, avgRatings.get(j+1));
                    avgRatings.set(j+1, temp);
                }
            }
        }
        for (int k = 0; k < avgRatings.size(); k++) {
            String id = avgRatings.get(k).getItem();
            String title = secRat.getTitle(id);
            double rating = avgRatings.get(k).getValue();
            System.out.println(rating + " " + title);
        }
    }
    public void getAverageRatingOneMovie() {
        String moviefile = "ratedmoviesfull.csv";
        String ratingsfile = "ratings.csv";
        SecondRatings secRat = new SecondRatings(moviefile, ratingsfile);
        String movie = "The Maze Runne";
        String idNo = secRat.getID(movie);
        ArrayList<Rating> ratings = secRat.getAverageRatings(0);
        for (int k = 0; k < ratings.size(); k++) {
            if (ratings.get(k).getItem().equals(idNo)) {
                System.out.println(movie + " " + ratings.get(k).getValue());
            }
        }
    }
}
