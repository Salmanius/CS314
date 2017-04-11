package main.java.edu.csu2017sp314.dtr17.Model;

import java.util.ArrayList;

import static java.lang.Math.toRadians;

/**
 * Created by chris cochran on 2/19/2017.
 */
public class TripMaker {

    //used to store all the locations
    private ArrayList<Location> locations;
    //used to store locations and remove them when they are visited
    private ArrayList<Location> locationsTemp;


    public TripMaker(ArrayList<Location> L){
        locations = L;
    }

    //creates the trip
    public Trip makeTrip(boolean twoOptBool, boolean threeOptBool){
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
                trip = newTrip;
            }
        }

        for(int k = 0; k < trip.getSize()-1; k++){
            int dist = calculateDistanceBetween(trip.getLoc(k), trip.getLoc(k+1));
            trip.setLegMileage(dist , k);
        }
        int dist = calculateDistanceBetween(trip.getLoc(trip.getSize()-1), trip.getLoc(0));
        trip.setLegMileage(trip.getSize()-1, dist);

        int newTotalMil = 0;
        for(int m = 0; m < trip.getSize(); m++){
            newTotalMil += trip.getLegMileage(m);
        }
        trip.setTotalMileage(newTotalMil);

        return trip;
    }

    //returns a nearest neighbor trip starting at start
    public Trip nearestNeighbor(int start){
        ArrayList<Location> visited = new ArrayList<>();
        locationsTemp = new ArrayList<>(locations);
        int i = start;
        visited.add(locationsTemp.get(i));

        while(visited.size() < locations.size()){
            int j = i;
            i = findNN(locationsTemp.get(i), i);
            visited.add(locationsTemp.get(i));
            locationsTemp.remove(j);
            if(i>j){
                i--;
            }
        }
        visited.add(locations.get(start));

        Trip trip = new Trip(false, false, false, visited.size());
        for(int k = 0; k < visited.size()-1; k++){
            trip.addLoc(visited.get(k), k, calculateDistanceBetween(visited.get(k), visited.get(k+1)));
        }
        trip.addLoc(visited.get(visited.size()-1), visited.size()-1, calculateDistanceBetween(visited.get(visited.size()-1), visited.get(1)));

        return trip;
    }

    //finds the nearest neighbor Location to current
    public int findNN(Location current, int currentNum){
        int nearest = -1;
        float shortestDistance = 10000000; //super big so first find is always smaller

        for (int i = 0; i < locationsTemp.size(); i++){
            if(i == currentNum){

            }
            else {
                float tempDistance = calculateDistanceBetween(current, locationsTemp.get(i));

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
                    if((calculateDistanceBetween(trip.getLoc(i), trip.getLoc(i+1)) + calculateDistanceBetween(trip.getLoc(j), trip.getLoc(j+1))) > (calculateDistanceBetween(trip.getLoc(i), trip.getLoc(j)) + calculateDistanceBetween(trip.getLoc(i+1), trip.getLoc(j+1)))){
                        reverse(i+1, j, trip);
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
                        int distNorm = calculateDistanceBetween(trip.getLoc(i), trip.getLoc(i+1)) + calculateDistanceBetween(trip.getLoc(j), trip.getLoc(j+1)) + calculateDistanceBetween(trip.getLoc(k), trip.getLoc(k+1));
                        int dist1 = calculateDistanceBetween(trip.getLoc(i), trip.getLoc(i+1)) + calculateDistanceBetween(trip.getLoc(j), trip.getLoc(k)) + calculateDistanceBetween(trip.getLoc(j+1), trip.getLoc(k+1));
                        int dist2 = calculateDistanceBetween(trip.getLoc(i), trip.getLoc(j)) + calculateDistanceBetween(trip.getLoc(i+1), trip.getLoc(j+1)) + calculateDistanceBetween(trip.getLoc(k), trip.getLoc(k+1));
                        int dist3 = calculateDistanceBetween(trip.getLoc(i), trip.getLoc(k)) + calculateDistanceBetween(trip.getLoc(j+1), trip.getLoc(j)) + calculateDistanceBetween(trip.getLoc(i+1), trip.getLoc(k+1));
                        int dist4 = calculateDistanceBetween(trip.getLoc(i), trip.getLoc(j+1)) + calculateDistanceBetween(trip.getLoc(k), trip.getLoc(i+1)) + calculateDistanceBetween(trip.getLoc(j), trip.getLoc(k+1));
                        int dist5 = calculateDistanceBetween(trip.getLoc(i), trip.getLoc(k)) + calculateDistanceBetween(trip.getLoc(j+1), trip.getLoc(i+1)) + calculateDistanceBetween(trip.getLoc(j), trip.getLoc(k+1));
                        int dist6 = calculateDistanceBetween(trip.getLoc(i), trip.getLoc(j+1)) + calculateDistanceBetween(trip.getLoc(k), trip.getLoc(j)) + calculateDistanceBetween(trip.getLoc(i+1), trip.getLoc(k+1));
                        int dist7 = calculateDistanceBetween(trip.getLoc(i), trip.getLoc(j)) + calculateDistanceBetween(trip.getLoc(i+1), trip.getLoc(k)) + calculateDistanceBetween(trip.getLoc(j+1), trip.getLoc(k+1));

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
            int milI = trip.getLegMileage(i);
            Location locJ = trip.getLoc(j);
            int milJ = trip.getLegMileage(j);
            trip.addLoc(locI, j, milI);
            trip.addLoc(locJ, i, milJ);
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
            trip.addLoc(sect0[c], c, 0);
            ++c;
        }
        while(c < j){
            trip.addLoc(sect1[c-i], c, 0);
            ++c;
        }
        while(c < k){
            trip.addLoc(sect2[c-j], c, 0);
            ++c;
        }
        while(c < l){
            trip.addLoc(sect3[c-k], c, 0);
            ++c;
        }
        while(c < trip.getSize()){
            trip.addLoc(sect4[c-l], c, 0);
            ++c;
        }
    }

    //calculates the distance between two Locations
    public int calculateDistanceBetween(Location A, Location B){
        double distance; //http://www.movable-type.co.uk/scripts/latlong.html
        double earthsRadiusMiles = 3958.756; //6371km
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
