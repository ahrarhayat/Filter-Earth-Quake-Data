
/**
 * Write a description of DistanceFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DistanceFilter implements Filter {
    private Location loc;
    private double maxDist;
    public DistanceFilter(Location loca, double dist)
    {
        loc=loca;
        maxDist=dist;
    }
    public boolean satisfies(QuakeEntry qe)
    {
        return qe.getLocation().distanceTo(loc)<maxDist;
    }
    
}
