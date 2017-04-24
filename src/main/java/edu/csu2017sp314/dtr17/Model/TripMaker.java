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

    protected double[][] distances;

    public TripMaker(ArrayList<Location> locations){
        this.locations = locations;

        distances = new double[locations.size()][locations.size()] ;

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

        populateTripMileages(trip);

        //find the shortest trip using nearest neighbor from each starting point
        for(int i = 0; i < locations.size(); i++){
            Trip newTrip = nearestNeighbor(i);
            if(threeOptBool){
                newTrip = threeOpt(newTrip);
            }
            else if(twoOptBool){
                newTrip = twoOpt(newTrip);
            }

            populateTripMileages(newTrip);

            if(newTrip.getMileage() < trip.getMileage()){
                System.out.println();
                System.out.println(newTrip.getMileage());
                System.out.println(trip.getMileage());
                trip = newTrip;
            }
        }

        return trip;
    }

    protected void populateTripMileages(Trip trip){
        int newTotalMil = 0;
        for(int m = 0; m < trip.getSize() - 1; m++){
            newTotalMil += distances[trip.getLoc(m).getIndex()][trip.getLoc(m+1).getIndex()];
        }
        trip.setTotalMileage(newTotalMil);
    }

    //returns a nearest neighbor trip starting at start
    public Trip nearestNeighbor(int start){
        int nnPosition = 0;
        int prevLocationIndex = start;
        Trip trip = new Trip(locations.size() + 1);

        trip.addLoc(locations.get(start));
        locations.get(start).setVisited(true);

        for(int i = 0; i < locations.size() -1; ++i){
            nnPosition = findNN(prevLocationIndex);
            trip.addLoc(locations.get(nnPosition));
            locations.get(nnPosition).setVisited(true);

            prevLocationIndex = nnPosition;
        }

        trip.addLoc(locations.get(start));

        for(int i = 0; i < locations.size(); ++i){
            locations.get(i).setVisited(false);
        }

        return trip;
    }

    //finds the nearest neighbor Location to current
    public int findNN(int position){
        int nearest = -1;
        double shortestDistance = 99999999; //super big so first find is always smaller

        for (int i = 0; i < locations.size(); i++){
            if(locations.get(i).isVisited() || i == position){
                //Do nothing, skip to next location
                continue;
            }
            else {
                double tempDistance = distances[position][i];

                if (tempDistance < shortestDistance) {
                    shortestDistance = tempDistance;
                    nearest = i;
                }
            }
        }

        return nearest;
    }

    public Trip twoOpt(Trip trip){
        boolean improvements = true;
        while(improvements){
            improvements = false;
            for(int i = 0; i < trip.getSize() - 3; i++){
                for(int j = i+2; j < trip.getSize() - 1; j++){
                    if((distances[trip.getLoc(i).getIndex()][trip.getLoc(i+1).getIndex()]
                            + distances[trip.getLoc(j).getIndex()][ trip.getLoc(j+1).getIndex()])
                            > (distances[trip.getLoc(i).getIndex()][trip.getLoc(j).getIndex()]
                            + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(j+1).getIndex()])){
                        trip.performTwoOptReverse(i+1, j);
                       improvements = true;
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
                        double distNorm =  distances[trip.getLoc(i).getIndex()][trip.getLoc(i+1).getIndex()]
                                + distances[trip.getLoc(j).getIndex()][trip.getLoc(j+1).getIndex()]
                                + distances[trip.getLoc(k).getIndex()][trip.getLoc(k+1).getIndex()];

                        double dist1 =  distances[trip.getLoc(i).getIndex()][trip.getLoc(i+1).getIndex()] +
                                + distances[trip.getLoc(j).getIndex()][trip.getLoc(k).getIndex()]
                                + distances[trip.getLoc(j+1).getIndex()][trip.getLoc(k+1).getIndex()];

                        double dist2 = distances[trip.getLoc(i).getIndex()][trip.getLoc(j).getIndex()]
                                + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(j+1).getIndex()]
                                + distances[trip.getLoc(k).getIndex()][trip.getLoc(k+1).getIndex()];

                        double dist3 = distances[trip.getLoc(i).getIndex()][trip.getLoc(k).getIndex()]
                                + distances[trip.getLoc(j+1).getIndex()][trip.getLoc(j).getIndex()]
                                + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(k+1).getIndex()];

                        double dist4 = distances[trip.getLoc(i).getIndex()][trip.getLoc(j+1).getIndex()]
                                + distances[trip.getLoc(k).getIndex()][trip.getLoc(i+1).getIndex()]
                                + distances[trip.getLoc(j).getIndex()][trip.getLoc(k+1).getIndex()];

                        double dist5 = distances[trip.getLoc(i).getIndex()][trip.getLoc(k).getIndex()]
                                + distances[trip.getLoc(j+1).getIndex()][trip.getLoc(i+1).getIndex()]
                                + distances[trip.getLoc(j).getIndex()][trip.getLoc(k+1).getIndex()];

                        double dist6 = distances[trip.getLoc(i).getIndex()][trip.getLoc(j+1).getIndex()]
                                + distances[trip.getLoc(k).getIndex()][trip.getLoc(j).getIndex()]
                                + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(k+1).getIndex()];

                        double dist7 = distances[trip.getLoc(i).getIndex()][trip.getLoc(j).getIndex()]
                                + distances[trip.getLoc(i+1).getIndex()][trip.getLoc(k).getIndex()]
                                + distances[trip.getLoc(j+1).getIndex()][trip.getLoc(k+1).getIndex()];

                        double distNormCopy = distNorm;
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
                                trip.performTwoOptReverse(j+1, k);
                                break;
                            case 2:
                                trip.performTwoOptReverse(i+1, j);
                                break;
                            case 3:
                                trip.performTwoOptReverse(i+1, k);
                                break;
                            case 4:
                                trip.swap(i+1, j, j+1, k);
                                break;
                            case 5:
                                trip.performTwoOptReverse(j+1,k);
                                trip.swap(i+1, j, j+1, k);
                                break;
                            case 6:
                                trip.performTwoOptReverse(i+1,j);
                                trip.swap(i+1, j, j+1, k);
                                break;
                            case 7:
                                trip.performTwoOptReverse(i+1, j);
                                trip.performTwoOptReverse(j+1, k);
                                break;
                        }
                    }
                }
            }
        }
        return trip;
    }

    //calculates the distance between two Locations
    public double calculateDistanceBetween(Location A, Location B){
        double distance; //http://www.movable-type.co.uk/scripts/latlong.html
        double earthsRadius;
        if(unitSelect){
             earthsRadius = 6372.8;
        }
        else{
            earthsRadius = 3959.87433;
        }
        double latARadians = Math.toRadians(A.getDblLatitude());
        double latBRadians = Math.toRadians(B.getDblLatitude());
        double changeInLat = Math.toRadians(B.getDblLatitude() - A.getDblLatitude());
        double changeInLong = Math.toRadians((B.getDblLongitude() - A.getDblLongitude()));

        double a = Math.sin(changeInLat/2) * Math.sin(changeInLat/2)
                + Math.cos(latARadians) * Math.cos(latBRadians)
                * Math.sin(changeInLong/2) * Math.sin(changeInLong/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        distance = (earthsRadius * c);

        return distance;
    }

    //returns the Locations at Index
    public Location getLocation(int index){
        return locations.get(index);
    }

}
