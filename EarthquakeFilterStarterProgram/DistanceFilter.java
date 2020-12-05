
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (Danielle) 
 * @version (7 August 2020)
 */
public class DistanceFilter implements Filter {
    private Location location;
    private double distMax;
    private String fname; 
    public DistanceFilter(Location loc, double max, String name) {
        location = loc;
        distMax = max;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getLocation().distanceTo(location) <= distMax;
    }
    public String getName() {
        return fname;
    }
}
