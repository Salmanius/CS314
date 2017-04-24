package main.java.edu.csu2017sp314.dtr17.Model;

/**
 * Created by mjdun on 2/19/2017.
 */
import java.util.ArrayList;

public class Trip {

    private Location[] trip;
    private int totalMileage;

    protected int currentLocationIndex = 0;


    public Trip(int size){
        trip = new Location[size];
        totalMileage = 0;
    }

    public void addLoc(Location location){

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

    public void swap(int i, int j, int k, int l){
        //array to hold the locations before i
        Location[] sect0 = new Location[i];
        //array to hold the first section of the array that will be swapped
        Location[] sect1 = new Location[j-i];
        //array to hold the locations between the two sections being swapped
        Location[] sect2 = new Location[k-j];
        //array to hold the third section of the array that will be swapped
        Location[] sect3 = new Location[l-k];
        //array to hold the locations after l
        Location[] sect4 = new Location[trip.length -l];

        int c = 0;
        while(c < i){
            sect0[c] = trip[c];
            ++c;
        }
        while(c < j){
            sect1[c-i] = trip[c];
            ++c;
        }
        while(c < k){
            sect2[c-j] = trip[c];
            ++c;
        }
        while(c < l){
            sect3[c-k] = trip[c];
            ++c;
        }
        while(c < trip.length){
            sect4[c-l] = trip[c];
            ++c;
        }

        c = 0;
        while(c < i){
            trip[c] = sect0[c];
            ++c;
        }
        while(c < j){
            trip[c] = sect1[c-i];
            ++c;
        }
        while(c < k){
            trip[c] = sect2[c-j];
            ++c;
        }
        while(c < l){
            trip[c] = sect3[c-k];
            ++c;
        }
        while(c < trip.length){
            trip[c] = sect4[c-l];
            ++c;
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
