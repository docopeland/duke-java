import java.util.*;
import edu.duke.*;

public class EarthQuakeClient2 {
    public EarthQuakeClient2() {
        // TODO Auto-generated constructor stub
    }
    public ArrayList<QuakeEntry> filter(ArrayList<QuakeEntry> quakeData, Filter f) { 
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData) { 
            if (f.satisfies(qe)) { 
                answer.add(qe); 
            } 
        } 
        return answer;
    } 
    public void quakesWithFilter() { 
        EarthQuakeParser parser = new EarthQuakeParser(); 
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);         
        System.out.println("read data for "+list.size()+" quakes");
        //default filter
        //Filter f = new MinMagFilter(5.0); 
        //ArrayList<QuakeEntry> m7  = filter(list, f); 
        //magnitude and depth filters
        Filter fil1 = new MagnitudeFilter(3.5, 4.5, "Magnitude");
        Filter fil2 = new DepthFilter(-55000.0, -20000.0, "Depth");
        ArrayList<QuakeEntry> filter1  = filter(list, fil1);
        ArrayList<QuakeEntry> filters = filter(filter1, fil2);
        //location and phrase filters
        // Location denver = new Location(39.7392, -104.9903);
        // Filter fil1 = new DistanceFilter(denver, 1000000, "dist");
        // Filter fil2 = new PhraseFilter("end", "a", "phrase");
        // ArrayList<QuakeEntry> filter1  = filter(list, fil1);
        // ArrayList<QuakeEntry> filters = filter(filter1, fil2);
        for (QuakeEntry qe: filters) { 
            System.out.println(qe);
        } 
        System.out.println("The number of earthquakes: " + filters.size());
    }
    public void createCSV() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "../data/nov20quakedata.atom";
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: "+list.size());
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
    public void testMatchAllFilter() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("# quakes read: "+list.size());
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(1.0, 4.0, "Magnitude");
        Filter f2 = new DepthFilter(-180000.0, -30000.0, "Depth");
        Filter f3 = new PhraseFilter("any", "o", "Phrase");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        } 
        System.out.println("The number of earthquakes: " + quakes.size());
        System.out.println("Filters used are:" + maf.getName());
    }
    public void testMatchAllFilter2() {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("# quakes read: "+list.size());
        MatchAllFilter maf = new MatchAllFilter();
        Filter f1 = new MagnitudeFilter(0.0, 5.0, "Magnitude");
        Location billund = new Location(55.7308, 9.1153);
        Filter f2 = new DistanceFilter(billund, 3000000, "Distance");
        Filter f3 = new PhraseFilter("any", "e", "Phrase");
        maf.addFilter(f1);
        maf.addFilter(f2);
        maf.addFilter(f3);
        ArrayList<QuakeEntry> quakes = filter(list, maf);
        for (QuakeEntry qe: quakes) { 
            System.out.println(qe);
        } 
        System.out.println("The number of earthquakes: " + quakes.size());
        System.out.println("Filters used are:" + maf.getName());
    }
}
