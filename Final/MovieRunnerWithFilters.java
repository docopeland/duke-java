import java.util.ArrayList;
import java.util.Collections;

public class MovieRunnerWithFilters {
    public void printAverageRatings() {
        String ratingsfile = "ratings_short.csv"; //""ratings.csv";
        String moviefile = "ratedmovies_short.csv"; //"ratedmoviesfull.csv";
        ThirdRatings thiRat = new ThirdRatings(ratingsfile);
        System.out.println("number of raters: " + thiRat.getRaterSize());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        System.out.println("avg ratings over 1: " );
        ArrayList<Rating> avgRatings = thiRat.getAverageRatings(1);
        System.out.println("found " + avgRatings.size() + " movies");
        Collections.sort(avgRatings);
        for (int k = 0; k < avgRatings.size(); k++) {
            String id = avgRatings.get(k).getItem();
            String title = movie.getTitle(id);
            double rating = avgRatings.get(k).getValue();
            System.out.println(rating + " " + title);
        }
    }
    public void printAverageRatingsByGenre() {
        String ratingsfile = "ratings_short.csv"; //""ratings.csv";
        String moviefile = "ratedmovies_short.csv"; //"ratedmoviesfull.csv";
        ThirdRatings thiRat = new ThirdRatings(ratingsfile);
        System.out.println("number of raters: " + thiRat.getRaterSize());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        System.out.println("avg ratings over 1: " );
        GenreFilter GF = new GenreFilter("Crime");
        ArrayList<Rating> avgRatings = thiRat.getAverageRatingsByFilter(1,GF);
        System.out.println("found " + avgRatings.size() + " movies");
        Collections.sort(avgRatings);
        for (int k = 0; k < avgRatings.size(); k++) {
            String id = avgRatings.get(k).getItem();
            String title = movie.getTitle(id);
            String genre = movie.getGenres(id);
            double rating = avgRatings.get(k).getValue();
            System.out.println(rating + " " + title + " " + genre);
        }
    }
    public void printAverageRatingsByMinutes() {
        String ratingsfile = "ratings_short.csv"; //""ratings.csv";
        String moviefile = "ratedmovies_short.csv"; //"ratedmoviesfull.csv";
        ThirdRatings thiRat = new ThirdRatings(ratingsfile);
        System.out.println("number of raters: " + thiRat.getRaterSize());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        System.out.println("avg ratings over 1: " );
        MinutesFilter MF = new MinutesFilter(110, 170);
        ArrayList<Rating> avgRatings = thiRat.getAverageRatingsByFilter(1, MF);
        System.out.println("found " + avgRatings.size() + " movies");
        Collections.sort(avgRatings);
        for (int k = 0; k < avgRatings.size(); k++) {
            String id = avgRatings.get(k).getItem();
            String title = movie.getTitle(id);
            int minutes = movie.getMinutes(id);
            double rating = avgRatings.get(k).getValue();
            System.out.println(rating + " Time: " + minutes + " " + title);
        }
    }
    public void printAverageRatingsByDirectors() {
        String ratingsfile = "ratings_short.csv"; //""ratings.csv";
        String moviefile = "ratedmovies_short.csv"; //"ratedmoviesfull.csv";
        ThirdRatings thiRat = new ThirdRatings(ratingsfile);
        System.out.println("number of raters: " + thiRat.getRaterSize());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        System.out.println("avg ratings over 1: " );
        DirectorsFilter DF = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> avgRatings = thiRat.getAverageRatingsByFilter(1, DF);
        System.out.println("found " + avgRatings.size() + " movies");
        Collections.sort(avgRatings);
        for (int k = 0; k < avgRatings.size(); k++) {
            String id = avgRatings.get(k).getItem();
            String title = movie.getTitle(id);
            String directors = movie.getDirector(id);
            double rating = avgRatings.get(k).getValue();
            System.out.println(rating + " " + title + " " + directors);
        }
    }
    public void printAverageRatingsByYearAfterAndGenre() {
        String ratingsfile = "ratings_short.csv"; //""ratings.csv";
        String moviefile = "ratedmovies_short.csv"; //"ratedmoviesfull.csv";
        ThirdRatings thiRat = new ThirdRatings(ratingsfile);
        System.out.println("number of raters: " + thiRat.getRaterSize());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        System.out.println("avg ratings over 1: " );
        YearAfterFilter YAF = new YearAfterFilter(1980);
        GenreFilter GF = new GenreFilter("Romance");
        AllFilters filters = new AllFilters();
        filters.addFilter(YAF);
        filters.addFilter(GF);
        ArrayList<Rating> avgRatings = thiRat.getAverageRatingsByFilter(1, filters);
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
    public void printAverageRatingsByDirectorsAndMinutes() {
        String ratingsfile = "ratings_short.csv"; //""ratings.csv";
        String moviefile = "ratedmovies_short.csv"; //"ratedmoviesfull.csv";
        ThirdRatings thiRat = new ThirdRatings(ratingsfile);
        System.out.println("number of raters: " + thiRat.getRaterSize());
        MovieDatabase movie = new MovieDatabase();
        movie.initialize(moviefile);
        System.out.println("number of movies: " + movie.size());
        System.out.println("avg ratings over 1: " );
        DirectorsFilter DF = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        MinutesFilter MF = new MinutesFilter(30, 170);
        AllFilters filters = new AllFilters();
        filters.addFilter(DF);
        filters.addFilter(MF);
        ArrayList<Rating> avgRatings = thiRat.getAverageRatingsByFilter(1, filters);
        System.out.println("found " + avgRatings.size() + " movies");
        Collections.sort(avgRatings);
        for (int k = 0; k < avgRatings.size(); k++) {
            String id = avgRatings.get(k).getItem();
            String title = movie.getTitle(id);
            String directors = movie.getDirector(id);
            int minutes = movie.getMinutes(id);
            double rating = avgRatings.get(k).getValue();
            System.out.println(rating + " " + title + " " + minutes + " " + directors);
        }
    }
}
