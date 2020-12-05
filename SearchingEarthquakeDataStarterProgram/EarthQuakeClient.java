import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }
    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            double mag = qe.getMagnitude();
            if (mag > magMin) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            Location loc = qe.getLocation();
            double dist = loc.distanceTo(from);
            if (dist < distMax) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth,
    double maxDepth) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            double depth = qe.getDepth();
            if (minDepth < depth && maxDepth > depth) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where,
    String phrase) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for (QuakeEntry qe : quakeData) {
            String title = qe.getInfo();
            if (where.equals("start") && title.startsWith(phrase)) {
                answer.add(qe);
            }
            if (where.equals("end") && title.endsWith(phrase)) {
                answer.add(qe);
            }
            if (where.equals("any") && title.indexOf(phrase)!= -1) {
                answer.add(qe);
            }
        }
        return answer;
    }
    public void quakesByPhrase() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        String where = "any";
        String phrase = "Can";
        ArrayList<QuakeEntry> phr = filterByPhrase(quakeData, where, phrase);
        for (QuakeEntry qe : phr) {
            System.out.println(qe);
        }
        System.out.println("the number of quakes that have the phrase " + phrase + " are " +
        phr.size());
    }
    public void quakesOfDepth() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> quakeData = parser.read(source);
        double minDepth = -4000.0;
        double maxDepth = -2000.0;
        ArrayList<QuakeEntry> depth = filterByDepth(quakeData, minDepth,maxDepth);
        for (QuakeEntry qe : depth) {
            System.out.println(qe);
        }
        System.out.println("The number of quakes that have a depth between " + minDepth + " and " 
        + maxDepth + " are " + depth.size());
    }
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }
    }
    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        double magMin = 5.0;
        ArrayList<QuakeEntry> magnitude = filterByMagnitude(list, magMin);
        System.out.println("the number of quakes higher than " + magMin + " magnitude is " 
        + magnitude.size());
        for (QuakeEntry qe : magnitude) {
            System.out.println(qe);
        }
    }
    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        //System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);
        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);
        //Location city  = new Location(-6.211,106.845);
        double distMax = 6000000.0;
        ArrayList<QuakeEntry> distance = filterByDistanceFrom(list, distMax, city);
        for (QuakeEntry qe : distance) {
            Location loc = qe.getLocation();
            double dist = loc.distanceTo(city);
            System.out.println(dist + "\t" + qe.getInfo());
        }
        System.out.println("there are " + distance.size() + " quakes that are " + distMax 
        + " away from my location");
    }
    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
}
