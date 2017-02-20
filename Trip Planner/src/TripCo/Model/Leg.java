package TripCo.Model;

/**
 * Created by mjdun on 2/19/2017.
 */
public class Leg {
    private int sequence;

    protected Location startLocation;
    protected Location endLocation;
    protected float mileage;

    public Leg(int sequence, float mileage){
        //purposely left most empty. Loading will be done with other methods to avoid 4 trillion args
        this.sequence = sequence;
        this.mileage = mileage;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
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

    public float getMileage() {
        return mileage;
    }
}
