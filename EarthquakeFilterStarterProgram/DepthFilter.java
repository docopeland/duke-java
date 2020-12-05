
/**
 * Write a description of DepthFilter here.
 * 
 * @author (Danielle) 
 * @version (7 August 2020)
 */
public class DepthFilter implements Filter {
    private double depMin;
    private double depMax;
    private String fname;
    public DepthFilter(double min, double max, String name) {
        depMin = min;
        depMax = max;
        fname = name;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getDepth() >= depMin && qe.getDepth() <= depMax;
    }
    public String getName() {
        return fname;
    }
}
