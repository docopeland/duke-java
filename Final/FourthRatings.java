import java.util.ArrayList;
import java.util.Collections;

public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters) {
        RaterDatabase myRaters = new RaterDatabase();
        myRaters.initialize("ratings.csv");
        double avg = 0;
        double ratings = 0;
        int raters = 0;
        for (Rater rates : myRaters.getRaters()) {
            if (rates.hasRating(id) == true) {
                ratings += rates.getRating(id);
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
    private double dotProduct(Rater me, Rater r) {
        double prod = 0;
        ArrayList<String> myItems = me.getItemsRated();
        ArrayList<String> rItems = r.getItemsRated();
        for (int k = 0; k < myItems.size(); k++) {
            String id = myItems.get(k);
            if (rItems.contains(id)) {
                double myRate = me.getRating(id);
                double rRate = r.getRating(id);
                prod += ((myRate-5) * (rRate-5));
            }
        }
        return prod;
    }
    private ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> sims = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for (Rater r : RaterDatabase.getRaters()) {
            if (! id.equals(r.getID())) {
                double dot = dotProduct(me, r);
                if (dot > 0) {
                    Rating newSims = new Rating(r.getID(),dot);
                    sims.add(newSims);
                }
            }
        }
        Collections.sort(sims, Collections.reverseOrder());
        return sims;
    }
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters, Filter filters) {
        ArrayList<Rating> simRatings = new ArrayList<Rating>();
        ArrayList<Rating> simRaters = getSimilarities(id);
        for (String movieID : MovieDatabase.filterBy(filters)) {
            double raters = 0;
            double ratings = 0;
            for (int k = 0; k < numSimilarRaters && k < simRaters.size(); k++) {
                Rater raterK = RaterDatabase.getRater(simRaters.get(k).getItem());
                if (raterK.hasRating(movieID) == true) {
                    ratings += (raterK.getRating(movieID) * simRaters.get(k).getValue());
                    raters++;
                }
            }
            if (raters >= minimalRaters) {
                double avg = ratings / raters;
                Rating simRate = new Rating(movieID, avg);
                simRatings.add(simRate);
            }
        }
        Collections.sort(simRatings,Collections.reverseOrder());
        return simRatings;
    }
}
