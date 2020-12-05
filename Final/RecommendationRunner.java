
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
public class RecommendationRunner implements Recommender{
    public ArrayList<String> getItemsToRate() {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        int[] moviePicker = new Random().ints(0, movies.size()).distinct().limit(20).toArray();
        ArrayList<String> firstMovies = new ArrayList<String>();
        for (int k = 0; k < moviePicker.length; k++) {
            firstMovies.add(movies.get(moviePicker[k]));
        }
        return firstMovies;
    }
    public void printRecommendationsFor(String webRaterID) {
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        FourthRatings fourRat = new FourthRatings();
        ArrayList<String> toRate = getItemsToRate();
        int numSimilarRaters = 30;
        int minimalRaters = 2;
        ArrayList<Rating> ratings = fourRat.getSimilarRatings(webRaterID, 
        numSimilarRaters, minimalRaters, new TrueFilter());
        if (ratings.size() < numSimilarRaters) {
            numSimilarRaters = ratings.size();
        }
        System.out.println("<p class='title'>Your Recommended Movies</p>");
        System.out.println("<style>.title {text-align: center; color: blue; font-size:3em; boder-top-style: dotted; border-bottom-style:dotted; border-color: orange; border-width: 2px; padding-left: 3px");
        System.out.println("<style>body{background-color: #ffffff;}</style>");
        if (ratings.size() == 0) {
            System.out.println("No movies found");
        }
        else {
            System.out.println("<table><tr><th class='column name'>TITLE</th><th class='column name'>MINUTES</th><th class='column name'>GENRE</th><th class='column name'>DIRECTORS</th><th class='column name'>YEAR</th><th class='column name'>RATING</th>");
            for (int k = 0; k < numSimilarRaters; k++) {
                String movieID = ratings.get(k).getItem();
                System.out.print("<tr><td>"+ MovieDatabase.getTitle(movieID) + "</td>");
                System.out.print("<td>" + MovieDatabase.getMinutes(movieID) + "</td>");
                System.out.print("<td>" + MovieDatabase.getGenres(movieID) + "</td>");
                System.out.print("<td>" + MovieDatabase.getDirector(movieID) + "</td>");
                System.out.print("<td>" + MovieDatabase.getYear(movieID) + "</td>");
                System.out.println("<td>" + ratings.get(k).getValue() + "</td></tr>");
            }
            System.out.println("</table>");
        }
    }
}
