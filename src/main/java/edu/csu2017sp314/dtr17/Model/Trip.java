package main.java.edu.csu2017sp314.dtr17.Model;

/**
 * Created by mjdun on 2/19/2017.
 */
import java.util.ArrayList;

public class Trip {

    private Location[] trip;
    private int[] legMileages;
    private boolean nameFlag;
    private boolean idFlag;
    private boolean mileageFlag;
    private int totalMileage;


    public Trip(boolean bName, boolean bID, boolean bMileage, int size){
        trip = new Location[size];
        legMileages = new int[size];
        nameFlag = bName;
        idFlag = bID;
        mileageFlag = bMileage;
        totalMileage = 0;
    }

    public void addLoc(Location L, int arraySpot, int mileage){
        trip[arraySpot] = L;
        legMileages[arraySpot] = mileage;
        totalMileage += mileage;
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

    public int getMileage(){
        return totalMileage;
    }

    public int getSize() {return trip.length;}

    public Location getLoc(int i) {return trip[i];}

    public int getLegMileage(int i) {return legMileages[i];}

    public void setLegMileage(int newMileage, int milSpot) {legMileages[milSpot] = newMileage;}

    public void setTotalMileage(int newMileage){totalMileage = newMileage;}

    public int getTotalMileage(){return totalMileage;}
}
