package main.java.edu.csu2017sp314.dtr17.Model;

/**
 * Created by mjdun on 2/19/2017.
 */

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

            trip[start] = trip[end];
            trip[end] = startLocation;

            ++start;
            --end;
        }
    }

    public void swap(int segOneStart, int segOneEnd, int segTwoStart, int segTwoEnd){
        //array to hold the first section of the array that will be swapped
        Location[] sect1 = new Location[segOneEnd-segOneStart + 1];
        //array to hold the third section of the array that will be swapped
        Location[] sect3 = new Location[segTwoEnd-segTwoStart + 1];

        //Store locations in temporary arrays.
        for(int i = segOneStart; i < segOneEnd + 1; ++i) {
            sect1[i - segOneStart] = trip[i];
        }

        for(int i = segTwoStart; i < segTwoEnd + 1; ++i){
            sect3[i - segTwoStart] = trip[i];
        }

        //do swaps

        //copy segment 3 to the beginning
        int i = segOneStart;
        int j = 0;

        while(i < segOneStart + sect3.length){
            trip[i] = sect3[j];
            ++i;
            ++j;
        }

        j = 0;
        int remaining = i + sect1.length;
        while (i < remaining){
            trip[i] = sect1[j];
            ++i;
            ++j;
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
}
