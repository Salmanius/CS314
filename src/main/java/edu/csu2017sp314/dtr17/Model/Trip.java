package main.java.edu.csu2017sp314.dtr17.Model;

/**
 * Created by mjdun on 2/19/2017.
 */
import java.util.ArrayList;
import main.java.edu.csu2017sp314.dtr17.Model.Leg;

public class Trip {

    private ArrayList<Leg> Legs;
    private boolean nameFlag;
    private boolean idFlag;
    private boolean mileageFlag;
    private int totalMileage;


    public Trip(boolean bName, boolean bID, boolean bMileage){
        Legs = new ArrayList<Leg>();
        nameFlag = bName;
        idFlag = bID;
        mileageFlag = bMileage;
        totalMileage = 0;
    }

    public void addLeg(Leg L){
        Legs.add(L);
        totalMileage += L.getMileage();
    }

    public void removeLeg(Leg L){
        Legs.remove(L);
        totalMileage -= L.getMileage();
    }

    public Leg getLeg(int index){
        return Legs.get(index);
    }

    public int amountOfLegs(){ return Legs.size(); }

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

    public int getMileage(){
        return totalMileage;
    }
}
