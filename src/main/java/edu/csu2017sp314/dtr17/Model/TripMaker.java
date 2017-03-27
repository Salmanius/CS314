package main.java.edu.csu2017sp314.dtr17.Model;

import java.util.ArrayList;

import static java.lang.Math.toRadians;

/**
 * Created by chris cochran on 2/19/2017.
 */
public class TripMaker {

    private ArrayList<Location> locations;
    private ArrayList<Location> locationsTemp;


    public TripMaker(ArrayList<Location> l){
        locations = l;
    }

    public Trip makeTrip(){
        Trip trip = new Trip(false, false, false, locations.size());
        trip = nearestNeighbor(0);

        //find the shortest trip using nearest neighbor from each starting point
        for(int i = 0; i < locations.size(); i++){
            Trip newTrip = nearestNeighbor(i);
            if(newTrip.getMileage() < trip.getMileage()){
                trip = newTrip;
            }
        }
        return trip;
    }

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

    public int findNN(Location current, int currentNum){
        int nearest = -1;
        float shortestDistance = 10000000; //super big so first find is always smaller

        for (int i = 0; i < locationsTemp.size(); i++){
            if(i == currentNum){

            }
            else {
                float tempDistance = calculateDistanceBetween(current, locations.get(i));

                if (tempDistance < shortestDistance) {
                    shortestDistance = tempDistance;
                    nearest = i;
                }
            }
        }

        return nearest;
    }

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

    public Location getLocation(int index){
        return locations.get(index);
    }

}
