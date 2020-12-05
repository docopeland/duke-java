
/**
 * Find N-largest quakes.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class LargestQuakes {
    public int indexOfLargest(ArrayList<QuakeEntry> data) {
        int index = 0;
        double magnitude = 0;
        for (QuakeEntry qe : data) {
            for (int k = 0; k < data.size(); k++) {
                double current = data.get(k).getMagnitude();
                if (current > magnitude) {
                    index = k;
                    magnitude = current;
                }
            }
        }
        return index;
    }
    public ArrayList<QuakeEntry> getLargest (ArrayList<QuakeEntry> quakeData, int howMany) {
        ArrayList<QuakeEntry> largest = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> copy = quakeData;
        for (int k = 0; k < howMany; k++) {
            if (copy.size() > 0) {
                int index = indexOfLargest(copy);
                double magnitude = copy.get(index).getMagnitude();
                largest.add(copy.get(index));
                copy.remove(index);
            }
            else {break;}
        }
        return largest;
    }
    public void findLargestQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //for (QuakeEntry qe : list) {
        //    System.out.println(qe);
        //}
        System.out.println("number found: "+ list.size());
        
        //int index = indexOfLargest(list);
        //System.out.println("index of largest magnitude is " + index + " with a magnitude of " 
        //+ list.get(index).getMagnitude());
        
        int howMany = 50;
        ArrayList<QuakeEntry> largest = getLargest(list, howMany);
        for (QuakeEntry qe : largest) {
            System.out.println(qe);
        }
    }
}
