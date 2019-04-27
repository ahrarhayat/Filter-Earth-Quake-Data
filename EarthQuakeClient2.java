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

        //Filter f = new MinMagFilter(4.0);
        Filter f = new MagnitudeFilter(4.0,5.0);
        ArrayList<QuakeEntry> m7  = filter(list, f); 
        f= new DepthFilter(-35000.0,-12000.0);
        ArrayList<QuakeEntry> m8  = filter(m7, f);
        
        Location Japan = new Location(35.42,139.43);
        Filter f1= new DistanceFilter(Japan,10000000);
        ArrayList<QuakeEntry> m9  = filter(list, f1);
        f1=new PhraseFilter("end","Japan");
        ArrayList<QuakeEntry> m10  = filter(m9, f1);
        for (QuakeEntry qe: m8) { 
            System.out.println(qe);
        } 
        System.out.println("Number of earth quakes found: "+m8.size());
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
    public void testMatchAllFilter()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        /*for(QuakeEntry qe: list)
        {
            System.out.println(qe);
        }
        */
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,2.0));
        maf.addFilter(new DepthFilter(-100000,-10000));
        maf.addFilter(new PhraseFilter("any","a"));
        ArrayList<QuakeEntry> filtered = filter(list,maf);
        for(QuakeEntry qe: filtered)
        {
            System.out.println(qe);
        }
        System.out.println("Number of earth quakes found: "+filtered.size());
    }
     public void testMatchAllFilter2()
    {
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");
        MatchAllFilter maf = new MatchAllFilter();
        maf.addFilter(new MagnitudeFilter(0.0,3.0));
        Location Oklahoma= new Location(36.1314, -95.9372);
        maf.addFilter(new DistanceFilter(Oklahoma,10000000));
        maf.addFilter(new PhraseFilter("any","Ca"));
        ArrayList<QuakeEntry> filtered = filter(list,maf);
        for(QuakeEntry qe: filtered)
        {
            System.out.println(qe);
        }
        System.out.println("Number of earth quakes found: "+filtered.size());
        System.out.println(maf.getName());
    }
   
}
