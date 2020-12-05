
/**
 * Write a description of class QuakeSortInPlace here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class QuakeSortInPlace {
    public QuakeSortInPlace() {
        // TODO Auto-generated constructor stub
    }
    public int getSmallestMagnitude(ArrayList<QuakeEntry> quakes, int from) {
        int minIdx = from;
        for (int i=from+1; i< quakes.size(); i++) {
            if (quakes.get(i).getMagnitude() < quakes.get(minIdx).getMagnitude()) {
                minIdx = i;
            }
        }
        return minIdx;
    }
    public void sortByMagnitude(ArrayList<QuakeEntry> in) {
       for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
        }
    }
    public int getLargestDepth(ArrayList<QuakeEntry> quakeData, int from) {
        int maxIndex = from;
        for (int k = from + 1; k < quakeData.size(); k++) {
            if (quakeData.get(k).getDepth() > quakeData.get(maxIndex).getDepth()) {
                maxIndex = k;
            }
        }
        return maxIndex;
    }
    public void sortByLargestDepth(ArrayList<QuakeEntry> quakeData) {
        for (int k = 0; k < 70; k++) {
            int maxIndex = getLargestDepth(quakeData, k);
            QuakeEntry qk = quakeData.get(k);
            QuakeEntry qmax = quakeData.get(maxIndex);
            quakeData.set(k, qmax);
            quakeData.set(maxIndex, qk);
        }
    }
    public void onePassBubbleSort(ArrayList<QuakeEntry> quakeData, int numSorted) {
       for (int k = 0; k < quakeData.size() - 1 - numSorted; k++) {
           if (quakeData.get(k).getMagnitude() > quakeData.get(k+1).getMagnitude()) {
                QuakeEntry qk = quakeData.get(k);
                QuakeEntry qk1 = quakeData.get(k+1);
                quakeData.set(k,qk1);
                quakeData.set(k+1, qk);
            }
        }
    }
    public void sortByMagnitudeWithBubbleSort(ArrayList<QuakeEntry> quakeData) {
        for (int k = 0; k < quakeData.size()-1; k++) {
            onePassBubbleSort(quakeData, k);
        }
    }
    public boolean checkInSortedOrder (ArrayList<QuakeEntry> quakeData) {
        for (int k = 0; k < quakeData.size()-1; k++) {
            if (quakeData.get(k).getMagnitude() > quakeData.get(k+1).getMagnitude()) {
                return false;
            }
        }
        return true;
    }
    public void sortByMagnitudeWithBubbleSortWithCheck(ArrayList<QuakeEntry> quakeData) {
        int passes = 0;
        for (int k = 0; k < quakeData.size()-1; k++) {
            onePassBubbleSort(quakeData, k);
            passes++;
            if (checkInSortedOrder(quakeData) == true) {
                break;
            }
        }
        System.out.println(passes);
    }
    public void sortByMagnitudeWithCheck(ArrayList<QuakeEntry> in) {
        int passes = 0;
        for (int i=0; i< in.size(); i++) {
            int minIdx = getSmallestMagnitude(in,i);
            QuakeEntry qi = in.get(i);
            QuakeEntry qmin = in.get(minIdx);
            in.set(i,qmin);
            in.set(minIdx,qi);
            passes++;
            if (checkInSortedOrder(in) == true) {
                break;
            }
        }
        System.out.println(passes);
    }
    public void testSort() {
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        //String source = "data/nov20quakedatasmall.atom";
        String source = "data/earthQuakeDataWeekDec6sample1.atom";
        //String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");    
        //sortByMagnitude(list);
        //sortByLargestDepth(list);
        sortByMagnitudeWithBubbleSortWithCheck(list);
        //sortByMagnitudeWithCheck(list);
        System.out.println("end result");
        for (QuakeEntry qe: list) { 
            System.out.println(qe);
        }
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
    }
    public void dumpCSV(ArrayList<QuakeEntry> list) {
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
            qe.getLocation().getLatitude(),
            qe.getLocation().getLongitude(),
            qe.getMagnitude(),
            qe.getInfo());
        }
    }
}
