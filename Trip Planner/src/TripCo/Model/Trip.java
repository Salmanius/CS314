package TripCo.Model;

/**
 * Created by mjdun on 2/19/2017.
 */
import java.util.ArrayList;
import TripCo.Model.Leg;

public class Trip {

    private ArrayList<Leg> Legs;
    private boolean nameFlag;
    private boolean idFlag;
    private boolean mileageFlag;


    public Trip(boolean bName, boolean bID, boolean bMileage){
        Legs = new ArrayList<Leg>();
        nameFlag = bName;
        idFlag = bID;
        mileageFlag = bMileage;

    }

    public void addLeg(Leg L){
        Legs.add(L);
    }

    public Leg getLeg(int index){
        return Legs.get(index);
    }

    public void setNameFlag(boolean nameFlag) {
        this.nameFlag = nameFlag;
    }

    public void setIdFlag(boolean idFlag) {
        this.idFlag = idFlag;
    }

    public void setMileageFlag(boolean mileageFlag) {
        this.mileageFlag = mileageFlag;
    }

    public boolean getNameFlag() {
        return nameFlag;
    }

    public boolean getIdFlag() {
        return idFlag;
    }

    public boolean getMileageFlag() {
        return mileageFlag;
    }
}
