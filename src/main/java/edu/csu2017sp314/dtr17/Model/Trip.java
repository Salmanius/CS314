package main.java.edu.csu2017sp314.dtr17.Model;

/**
 * Created by mjdun on 2/19/2017.
 */
import java.util.ArrayList;

public class Trip {

    private Location[] trip;
    private int[] legMileages;
    private int totalMileage;

    protected int currentLocationIndex = 0;
    protected int currentLegIndex = 0;


    public Trip(int size){
        trip = new Location[size];
        legMileages = new int[size-1];
        totalMileage = 0;
    }

    public void addLoc(Location location, int distanceFromPreviousLocation){
        if(distanceFromPreviousLocation != -1){
            legMileages[currentLegIndex] = distanceFromPreviousLocation;
            totalMileage += distanceFromPreviousLocation;
            ++currentLegIndex;
        }

        trip[currentLocationIndex] = location;
        ++currentLocationIndex;

    }

    public void performTwoOptReverse(int start, int end){
        while(start < end) {
            Location startLocation = trip[start];


            //trip.addLoc(locI, j);
            trip[start] = trip[end];
            //trip.addLoc(locJ, i);
            trip[end] = startLocation;


            ++start;
            --end;
        }
    }

    protected void recalculateDistances(int start, int end){
        for(int i = start; i < end; ++i){
            if(i == 0){
                continue;
            }
            //legMileages[i] =
        }
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

    /*protected int calculateDistanceBetween(Location A, Location B){
        double distance; //http://www.movable-type.co.uk/scripts/latlong.html
        double earthsRadiusMiles;
        if(unitSelect){
            earthsRadiusMiles = 6372.8;
        }
        else{
            earthsRadiusMiles = 3959.87433;
        }
        double latARadians = Math.toRadians(A.getDblLatitude());
        double latBRadians = Math.toRadians(B.getDblLatitude());
        double changeInLat = Math.toRadians(B.getDblLatitude() - A.getDblLatitude());
        double changeInLong = Math.toRadians((B.getDblLongitude() - A.getDblLongitude()));

        double a = Math.sin(changeInLat/2) * Math.sin(changeInLat/2)
                + Math.cos(latARadians) * Math.cos(latBRadians)
                * Math.sin(changeInLong/2) * Math.sin(changeInLong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        distance = (earthsRadiusMiles * c);

        return (int) Math.round(distance);
    }*/
}
