
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    public ArrayList<Movie> loadMovie(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (CSVRecord record : fr.getCSVParser(true)) {
            // get all the info individually and save them to strings
            String ID = record.get("id");
            String title = record.get("title");
            String year = record.get("year");
            String country = record.get("country");
            String genres = record.get("genre");
            String directors = record.get("director");
            int minutes = Integer.parseInt(record.get("minutes"));
            String poster = record.get("poster");
            Movie mov = new Movie (ID, title, year, genres, directors, country, poster, 
            minutes);
            movies.add(mov);
        }
        return movies;
    }
    public void testLoadMovies() {
        ArrayList<Movie> movies = loadMovie("ratedmoviesfull.csv");
        System.out.println("Movies #: " + movies.size());
        //for (Movie m : movies) {
        //    System.out.println(m);
        //}
        String genreType = "Comedy";
        int genreNo = 0;
        int minLen = 150;
        int minNo = 0;
        HashMap<String, Integer> direct = new HashMap<String, Integer>();
        int directed = 0;
        ArrayList<String> directors = new ArrayList<String>();
        for (Movie m: movies) {
            if (m.getGenres().contains(genreType)) {
                genreNo++;
            }
            if (m.getMinutes() > minLen) {
                minNo++;
            }
            String[] directs = m.getDirector().split(",\\s*");
            for (int k = 0; k < directs.length; k++) {
                if (! direct.containsKey(directs[k])) {
                    direct.put(directs[k],1);
                }
                else {
                    direct.put(directs[k], direct.get(directs[k])+1);
                }
            }
        }
        for (String dir : direct.keySet()) {
            if (direct.get(dir) > directed) {
                directors.clear();
                directors.add(dir);
                directed = direct.get(dir);
            }
            if (direct.get(dir) == directed && (! directors.contains(dir))) {
                directors.add(dir);
            }
        }
        System.out.println(genreNo + " movies of genre " + genreType);
        System.out.println(minNo + " movies longer than " + minLen);
        System.out.println(directors.size() + " directed the max of " + directed + 
        " movies : " + directors);
    }
    public ArrayList<Rater> loadRaters(String filename) {
        FileResource fr = new FileResource("data/" + filename);
        ArrayList<Rater> raters= new ArrayList<Rater>();
        int no = 0;
        for (CSVRecord record : fr.getCSVParser()) {
            String raterID = record.get("rater_id");
            String movieID = record.get("movie_id");
            double rating = Integer.parseInt(record.get("rating"));
            Rater raterNew = new EfficientRater(raterID);
            if (raters.contains(raterNew)) {
                int k = raters.indexOf(raterNew);
                raters.get(k).addRating(movieID,rating);
            }
            else {
                raterNew.addRating(movieID, rating);
                raters.add(raterNew);
            }
        }
        return raters;
    }
    public void testLoadRaters() {
        ArrayList<Rater> raters = loadRaters("ratings.csv");
        System.out.println("There are " + raters.size() + " raters.");
        /** for (int k = 0; k < raters.size(); k++) {
            System.out.println(raters.get(k).getID() + " has " + raters.get(k).numRatings()
            + " ratings");
            ArrayList<String> ratings = raters.get(k).getItemsRated();
            for (String r : ratings) {
                double i = raters.get(k).getRating(r);
                System.out.println("[" + r + ", " + i + "]");
            }
        } */
        Rater ARater = new EfficientRater("193");
        if (raters.contains(ARater)) {
            int indx = raters.indexOf(ARater);
            int rates = raters.get(indx).numRatings();
            System.out.println("Rater " + ARater.getID() + " ratings " + rates);
        }
        int ratings = 0;
        ArrayList<String> rID = new ArrayList<String>();
        for (int k = 0; k < raters.size(); k++) {
            if (raters.get(k).numRatings() > ratings) {
                rID.clear();
                ratings = raters.get(k).numRatings();
                rID.add(raters.get(k).getID());
            }
            if (raters.get(k).numRatings() == ratings && 
            (!rID.contains(raters.get(k).getID()))) {
                rID.add(raters.get(k).getID());
            }
        };
        System.out.println("Rater " + rID + " has the max of " + ratings + " ratings");
        String move = "1798709";
        int rates = 0;
        for (int k = 0; k < raters.size(); k++) {
            if (raters.get(k).hasRating(move) == true) {
                rates++;
            }
        }
        System.out.println("Movie " + move + " has " + rates + " ratings");
        ArrayList<String> movies = new ArrayList<String>();
        for (int k = 0; k < raters.size(); k++) {
            ArrayList<String> rated = raters.get(k).getItemsRated();
            for (int i = 0; i < rated.size(); i++) {
                if (! movies.contains(rated.get(i))) {
                    movies.add(rated.get(i));
                }
            }
        }
        System.out.println(movies.size() + " different movies have been rated");
    }
}