import sun.java2d.loops.GeneralRenderer;

import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings() {
        String ratingsfile = "ratings.csv";
        String moviefile = "ratedmoviesfull.csv";
        FourthRatings fourRat = new FourthRatings();
        RaterDatabase rateData = new RaterDatabase();
        rateData.initialize(ratingsfile);
        System.out.println("number of raters: " + rateData.size());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        System.out.println("avg ratings over 1: " );
        ArrayList<Rating> avgRatings = fourRat.getAverageRatings(1);
        System.out.println("found " + avgRatings.size() + " movies");
        Collections.sort(avgRatings);
        for (int k = 0; k < avgRatings.size(); k++) {
            String id = avgRatings.get(k).getItem();
            String title = movie.getTitle(id);
            double rating = avgRatings.get(k).getValue();
            System.out.println(rating + " " + title);
        }
    }
    public void printAverageRatingsByYearAfterAndGenre() {
        String ratingsfile = "ratings.csv";
        String moviefile = "ratedmoviesfull.csv";
        FourthRatings fourRat = new FourthRatings();
        RaterDatabase rateData = new RaterDatabase();
        rateData.initialize(ratingsfile);
        System.out.println("number of raters: " + rateData.size());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        System.out.println("avg ratings over 1: " );
        YearAfterFilter YAF = new YearAfterFilter(1980);
        GenreFilter GF = new GenreFilter("Romance");
        AllFilters filters = new AllFilters();
        filters.addFilter(YAF);
        filters.addFilter(GF);
        ArrayList<Rating> avgRatings = fourRat.getAverageRatingsByFilter(1, filters);
        System.out.println("found " + avgRatings.size() + " movies");
        Collections.sort(avgRatings);
        for (int k = 0; k < avgRatings.size(); k++) {
            String id = avgRatings.get(k).getItem();
            String title = movie.getTitle(id);
            int year = movie.getYear(id);
            String genres = movie.getGenres(id);
            double rating = avgRatings.get(k).getValue();
            System.out.println(rating + " " + title + " " + year + " " + genres);
        }
    }
    public void printSimilarRatings() {
        String ratingsfile = "ratings.csv";
        String moviefile = "ratedmoviesfull.csv";
        FourthRatings fourRat = new FourthRatings();
        RaterDatabase rateData = new RaterDatabase();
        rateData.initialize(ratingsfile);
        System.out.println("number of raters: " + rateData.size());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        String raterID = "71";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        TrueFilter TF = new TrueFilter();
        ArrayList<Rating> ratings = fourRat.getSimilarRatings(raterID, numSimilarRaters, minimalRaters, TF);
        if (ratings.size() < numSimilarRaters) {
            numSimilarRaters = ratings.size();
        }
        for (int k = 0; k < numSimilarRaters; k++) {
            String movieID = ratings.get(k).getItem();
            String title = movie.getTitle(movieID);
            double rating = ratings.get(k).getValue();
            System.out.println(rating + " " + title);
        }
    }
    public void printSimilarRatingsByGenre() {
        String ratingsfile = "ratings.csv"; //""ratings.csv";
        String moviefile = "ratedmoviesfull.csv"; //"ratedmoviesfull.csv";
        FourthRatings fourRat = new FourthRatings();
        RaterDatabase rateData = new RaterDatabase();
        rateData.initialize(ratingsfile);
        System.out.println("number of raters: " + rateData.size());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        String raterID = "964";
        int numSimilarRaters = 20;
        int minimalRaters = 5;
        GenreFilter GF = new GenreFilter("Mystery");
        ArrayList<Rating> ratings = fourRat.getSimilarRatings(raterID, numSimilarRaters, minimalRaters, GF);
        if (ratings.size() < numSimilarRaters) {
            numSimilarRaters = ratings.size();
        }
        for (int k = 0; k < numSimilarRaters; k++) {
            String movieID = ratings.get(k).getItem();
            String title = movie.getTitle(movieID);
            double rating = ratings.get(k).getValue();
            System.out.println(title + " " + rating);
        }
    }
    public void printSimilarRatingsByDirector() {
        String ratingsfile = "ratings.csv"; //""ratings.csv";
        String moviefile = "ratedmoviesfull.csv"; //"ratedmoviesfull.csv";
        FourthRatings fourRat = new FourthRatings();
        RaterDatabase rateData = new RaterDatabase();
        rateData.initialize(ratingsfile);
        System.out.println("number of raters: " + rateData.size());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        String raterID = "120";
        int numSimilarRaters = 10;
        int minimalRaters = 2;
        DirectorsFilter DF = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ratings = fourRat.getSimilarRatings(raterID, numSimilarRaters, minimalRaters, DF);
        if (ratings.size() < numSimilarRaters) {
            numSimilarRaters = ratings.size();
        }
        for (int k = 0; k < numSimilarRaters; k++) {
            String movieID = ratings.get(k).getItem();
            String title = movie.getTitle(movieID);
            double rating = ratings.get(k).getValue();
            System.out.println(title + " " + rating);
        }
    }
    public void printSimilarRatingsByGenreAndMinutes() {
        String ratingsfile = "ratings.csv"; //""ratings.csv";
        String moviefile = "ratedmoviesfull.csv"; //"ratedmoviesfull.csv";
        FourthRatings fourRat = new FourthRatings();
        RaterDatabase rateData = new RaterDatabase();
        rateData.initialize(ratingsfile);
        System.out.println("number of raters: " + rateData.size());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        String raterID = "168";
        int numSimilarRaters = 10;
        int minimalRaters = 3;
        AllFilters AF = new AllFilters();
        GenreFilter GF = new GenreFilter("Drama");
        AF.addFilter(GF);
        MinutesFilter MF = new MinutesFilter(80,160);
        AF.addFilter(MF);
        ArrayList<Rating> ratings = fourRat.getSimilarRatings(raterID, numSimilarRaters, minimalRaters, AF);
        if (ratings.size() < numSimilarRaters) {
            numSimilarRaters = ratings.size();
        }
        for (int k = 0; k < numSimilarRaters; k++) {
            String movieID = ratings.get(k).getItem();
            String title = movie.getTitle(movieID);
            double rating = ratings.get(k).getValue();
            System.out.println(title + " " + rating);
        }
    }
    public void printSimilarRatingsByYearAfterAndMinutes() {
        String ratingsfile = "ratings.csv"; //""ratings.csv";
        String moviefile = "ratedmoviesfull.csv"; //"ratedmoviesfull.csv";
        FourthRatings fourRat = new FourthRatings();
        RaterDatabase rateData = new RaterDatabase();
        rateData.initialize(ratingsfile);
        System.out.println("number of raters: " + rateData.size());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        String raterID = "314";
        int numSimilarRaters = 10;
        int minimalRaters = 5;
        AllFilters AF = new AllFilters();
        YearAfterFilter YAF = new YearAfterFilter(1975);
        AF.addFilter(YAF);
        MinutesFilter MF = new MinutesFilter(70,200);
        AF.addFilter(MF);
        ArrayList<Rating> ratings = fourRat.getSimilarRatings(raterID, numSimilarRaters, minimalRaters, AF);
        if (ratings.size() < numSimilarRaters) {
            numSimilarRaters = ratings.size();
        }
        for (int k = 0; k < numSimilarRaters; k++) {
            String movieID = ratings.get(k).getItem();
            String title = movie.getTitle(movieID);
            double rating = ratings.get(k).getValue();
            System.out.println(title + " " + rating);
        }
    }
}
