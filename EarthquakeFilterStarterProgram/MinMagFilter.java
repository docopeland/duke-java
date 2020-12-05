
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (Danielle) 
 * @version (7 August 2020)
 */
public class MinMagFilter implements Filter
{
    private double magMin;
    private String fname;
    public MinMagFilter(double min, String name) { 
        magMin = min;
        fname = name;
    } 
    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 
    public String getName() {
        return fname;
    }
}
