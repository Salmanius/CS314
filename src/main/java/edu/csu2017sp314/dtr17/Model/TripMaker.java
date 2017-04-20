package main.java.edu.csu2017sp314.dtr17.Model;

import java.util.ArrayList;
import java.util.LinkedList;

import static java.lang.Math.log;
import static java.lang.Math.toRadians;

/**
 * Created by chris cochran on 2/19/2017.
 */
public class TripMaker {

    //used to store all the locations
    private ArrayList<Location> locations;

    //False is for Miles, True is for km
    private boolean unitSelect;

    protected int[][] distances;

    public TripMaker(ArrayList<Location> locations){
        this.locations = locations;

        distances = new int[locations.size()][locations.size()];

        //initialize distance lookup array
        for(int i = 0; i < locations.size(); ++i){
            locations.get(i).setIndex(i);
            for(int j = 0; j < locations.size(); ++j){
                distances[i][j] = calculateDistanceBetween(locations.get(i), locations.get(j));
            }
        }

    }

    //creates the trip
    public Trip makeTrip(boolean twoOptBool, boolean threeOptBool, boolean unit){
        unitSelect = unit;
        Trip trip;
        trip = nearestNeighbor(0);
        if(threeOptBool){
            trip = threeOpt(trip);
        }
        else if(twoOptBool){
            trip = twoOpt(trip);
        }

        //find the shortest trip using nearest neighbor from each starting point
        for(int i = 0; i < locations.size(); i++){
            Trip newTrip = nearestNeighbor(i);
            if(threeOptBool){
                newTrip = threeOpt(newTrip);
            }
            else if(twoOptBool){
                newTrip = twoOpt(newTrip);
            }

            if(newTrip.getMileage() < trip.getMileage()){
                System.out.println();
                System.out.println(newTrip.getMileage());
                System.out.println(trip.getMileage());
                trip = newTrip;
            }
        }

        for(int k = 0; k < trip.getSize()-1; k++){
            int dist = distances[trip.getLoc(k).getIndex()][trip.getLoc(k+1).getIndex()];
            trip.setLegMileage(dist , k);
        }

        int newTotalMil = 0;
        for(int m = 0; m < trip.getSize() - 1; m++){
            newTotalMil += trip.getLegMileage(m);
        }
        trip.setTotalMileage(newTotalMil);

        return trip;
    }

    //returns a nearest neighbor trip starting at start
    public Trip nearestNeighbor(int start){
        int nnPosition = 0;
        int prevLocationIndex = start;
        Trip trip = new Trip(locations.size());

        trip.addLoc(locations.get(start), -1);
        locations.get(start).setVisited(true);

        for(int i = 0; i < locations.size() -1 ; ++i){
            nnPosition = findNN(prevLocationIndex);
            trip.addLoc(locations.get(nnPosition), distances[prevLocationIndex][nnPosition]);
            locations.get(nnPosition).setVisited(true);


            prevLocationIndex = nnPosition;
        }

        for(int i = 0; i < locations.size(); ++i){
            locations.get(i).setVisited(false);
        }

        return trip;
    }

    //finds the nearest neighbor Location to current
    public int findNN(int position){
        int nearest = -1;
        float shortestDistance = 99999999; //super big so first find is always smaller

        for (int i = 0; i < locations.size(); i++){
            if(locations.get(i).isVisited() || i == position){
                //Do nothing, skip to next location
            }
            else {
                float tempDistance = distances[position][i];

                if (tempDistance < shortestDistance) {
                    shortestDistance = tempDistance;
                    nearest = i;
                }
            }
        }

        return nearest;
    }

    public Trip twoOpt(Trip trip){
        int improvements = 1;
        while(improvements > 0){
            improvements = 0;
            for(int i = 0; i < trip.getSize() - 3; i++){
                for(int j = i+2; j < trip.getSize() - 1; j++){
                    if((distances[trip.getLoc(i).getIndex()][trip.getLoc(i+1).getIndex()]
                            + distances[trip.getLoc(j).getIndex()][ trip.getLoc(j+1).getIndex()])
                            > (distances[trip.getLoc(i).getIndex()][trip.getLoc(j).getIndex()]
                                + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(j+1).getIndex()])){
                        trip.performTwoOptReverse(i+1, j);
                        ++improvements;
                    }
                }
            }
        }

        return trip;
    }

    public Trip threeOpt(Trip trip){
        int improvements = 1;
        int lastImprovements = -1;
        int looping = 0;
        while(improvements > 0 && looping < 1){
            if(lastImprovements == improvements){
                ++looping;
            }
            lastImprovements = improvements;
            improvements = 0;
            for(int i = 0; i < trip.getSize() - 5; ++i){
                for(int j = i+2; j < trip.getSize() - 3; ++j){
                    for(int k = j+2; k < trip.getSize() - 1; ++k){
                        int distNorm =  distances[trip.getLoc(i).getIndex()][trip.getLoc(i+1).getIndex()]
                                + distances[trip.getLoc(j).getIndex()][trip.getLoc(j+1).getIndex()]
                                + distances[trip.getLoc(k).getIndex()][trip.getLoc(k+1).getIndex()];
                        
                        int dist1 =  distances[trip.getLoc(i).getIndex()][trip.getLoc(i+1).getIndex()] +
                                + distances[trip.getLoc(j).getIndex()][trip.getLoc(k).getIndex()]
                                + distances[trip.getLoc(j+1).getIndex()][trip.getLoc(k+1).getIndex()];
                        
                        int dist2 = distances[trip.getLoc(i).getIndex()][trip.getLoc(j).getIndex()]
                                + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(j+1).getIndex()]
                                + distances[trip.getLoc(k).getIndex()][trip.getLoc(k+1).getIndex()];
                        
                        int dist3 = distances[trip.getLoc(i).getIndex()][trip.getLoc(k).getIndex()]
                                + distances[trip.getLoc(j+1).getIndex()][trip.getLoc(j).getIndex()]
                                + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(k+1).getIndex()];
                        
                        int dist4 = distances[trip.getLoc(i).getIndex()][trip.getLoc(j+1).getIndex()]
                                + distances[trip.getLoc(k).getIndex()][trip.getLoc(i+1).getIndex()]
                                + distances[trip.getLoc(j).getIndex()][trip.getLoc(k+1).getIndex()];
                        
                        int dist5 = distances[trip.getLoc(i).getIndex()][trip.getLoc(k).getIndex()]
                                + distances[trip.getLoc(j+1).getIndex()][trip.getLoc(i+1).getIndex()]
                                + distances[trip.getLoc(j).getIndex()][trip.getLoc(k+1).getIndex()];
                       
                        int dist6 = distances[trip.getLoc(i).getIndex()][trip.getLoc(j+1).getIndex()]
                                + distances[trip.getLoc(k).getIndex()][trip.getLoc(j).getIndex()]
                                + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(k+1).getIndex()];
                        
                        int dist7 = distances[trip.getLoc(i).getIndex()][trip.getLoc(j).getIndex()]
                                + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(k).getIndex()]
                                + distances[trip.getLoc(j+1).getIndex()][trip.getLoc(k+1).getIndex()];

                        int distNormCopy = distNorm;
                        int selection = 0;
                        if(dist1 < distNormCopy){
                            distNormCopy = dist1;
                            selection = 1;
                            ++improvements;
                        }
                        if(dist2 < distNormCopy){
                            distNormCopy = dist2;
                            selection = 2;
                            ++improvements;
                        }
                        if(dist3 < distNormCopy){
                            distNormCopy = dist3;
                            selection = 3;
                            ++improvements;
                        }
                        if(dist4 < distNormCopy){
                            distNormCopy = dist4;
                            selection = 4;
                            ++improvements;
                        }
                        if(dist5 < distNormCopy){
                            distNormCopy = dist5;
                            selection = 5;
                            ++improvements;
                        }
                        if(dist6 < distNormCopy){
                            distNormCopy = dist6;
                            selection = 6;
                            ++improvements;
                        }
                        if(dist7 < distNormCopy){
                            distNormCopy = dist7;
                            selection = 7;
                            ++improvements;
                        }

                        switch(selection){
                            case 0:
                                break;
                            case 1:
                                reverse(j+1, k, trip);
                                break;
                            case 2:
                                reverse(i+1, j, trip);
                                break;
                            case 3:
                                reverse(i+1, k, trip);
                                break;
                            case 4:
                                swap(i+1, j, j+1, k, trip);
                                break;
                            case 5:
                                reverse(j+1, k, trip);
                                swap(i+1, j, j+1, k, trip);
                                break;
                            case 6:
                                reverse(i+1, j, trip);
                                swap(i+1, j, j+1, k, trip);
                                break;
                            case 7:
                                reverse(i+1, j, trip);
                                reverse(j+1, k, trip);
                                break;
                        }
                    }
                }
            }
        }
        return trip;
    }

    //reverses the locations between the two points in the trip
    public void reverse(int i, int j, Trip trip){
        while(i < j) {
            Location locI = trip.getLoc(i);

            Location locJ = trip.getLoc(j);

            trip.addLoc(locI, j);
            trip.addLoc(locJ, i);

            ++i;
            --j;
        }

    }

    //swaps the sets (i, j) with (k, l) in the trip
    public void swap(int i, int j, int k, int l, Trip trip){
        //array to hold the locations before i
        Location[] sect0 = new Location[i];
        //array to hold the first section of the array that will be swapped
        Location[] sect1 = new Location[j-i];
        //array to hold the locations between the two sections being swapped
        Location[] sect2 = new Location[k-j];
        //array to hold the third section of the array that will be swapped
        Location[] sect3 = new Location[l-k];
        //array to hold the locations after l
        Location[] sect4 = new Location[trip.getSize()-l];

        int c = 0;
        while(c < i){
            sect0[c] = trip.getLoc(c);
            ++c;
        }
        while(c < j){
            sect1[c-i] = trip.getLoc(c);
            ++c;
        }
        while(c < k){
            sect2[c-j] = trip.getLoc(c);
            ++c;
        }
        while(c < l){
            sect3[c-k] = trip.getLoc(c);
            ++c;
        }
        while(c < trip.getSize()){
            sect4[c-l] = trip.getLoc(c);
            ++c;
        }

        c = 0;
        while(c < i){
            trip.addLoc(sect0[c], c);
            ++c;
        }
        while(c < j){
            trip.addLoc(sect1[c-i], c);
            ++c;
        }
        while(c < k){
            trip.addLoc(sect2[c-j], c);
            ++c;
        }
        while(c < l){
            trip.addLoc(sect3[c-k], c);
            ++c;
        }
        while(c < trip.getSize()){
            trip.addLoc(sect4[c-l], c);
            ++c;
        }
    }

    //calculates the distance between two Locations
    public int calculateDistanceBetween(Location A, Location B){
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
    }

    //returns the Locations at Index
    public Location getLocation(int index){
        return locations.get(index);
    }

}
