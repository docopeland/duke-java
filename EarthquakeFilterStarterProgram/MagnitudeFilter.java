
/**
 * Write a description of MagnitudeFilter here.
 * 
 * @author (Danielle) 
 * @version (7 August 2020)
 */
public class MagnitudeFilter implements Filter {
    private double magMin;
    private double magMax;
    private String fname;
    public MagnitudeFilter(double min, double max, String name) {
        magMin = min;
        magMax = max;
        fname = name;
    }
    public boolean satisfies(QuakeEntry qe) {
        return qe.getMagnitude() >= magMin && qe.getMagnitude() <= magMax;
    }
    public String getName() {
        return fname;
    }
}
