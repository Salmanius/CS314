package main.java.edu.csu2017sp314.dtr17.Model;

/**
 * Created by mjdun on 2/19/2017.
 */
public class Leg {
    private int sequence;

    protected Location startLocation;
    protected Location endLocation;
    protected int mileage;

    public Leg(int sequence, int mileage){
        this.sequence = sequence;
        this.mileage = mileage;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public int getSequence() {
        return sequence;
    }

    public String getStartName() {
        return startLocation.getName();
    }

    public String getEndName() {
        return endLocation.getName();
    }

    public int getMileage() {
        return mileage;
    }
}
