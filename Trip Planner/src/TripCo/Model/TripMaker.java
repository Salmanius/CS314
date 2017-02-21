package TripCo.Model;

import java.util.ArrayList;

import static java.lang.Math.toRadians;

/**
 * Created by chris cochran on 2/19/2017.
 */
public class TripMaker {

    private ArrayList<Location> locations;
    private ArrayList<Location> visited;


    public TripMaker(ArrayList<Location> l){
        locations = l;
        visited = new ArrayList<Location>();
    }

    public Trip makeNNTrip(){
        Trip NNTrip = new Trip(false, false, false);


        Location current = locations.get(0); //mark first location as current
        visited.add(current); //mark current location as visited
        locations.remove(0); // remove from list of locations to visit

        int i = 0;
        while (locations.size() > 0){ //looping through the locations removing as it visits them
            int nn = findNN(current);
            Leg L = makeLeg(current,locations.get(nn),i); // use zero for first sequence and +1 per loop after
            i++;
            current = locations.get(nn);
            visited.add(current); //mark current location as visited
            locations.remove(nn); // remove from list of locations to visit

            NNTrip.addLeg(L); // add leg to trip
        }

        //loop the end back to the beginning
        i++; //increment the sequence 1 last time
        Leg L = makeLeg(current,visited.get(0),i); //make leg from current (last item we used) back to the very first visited
        NNTrip.addLeg(L); //add last leg to trip
        //return created trip
        return NNTrip;
    }

    public int findNN(Location current){
        int nearest = 0;
        float shortestDistance = 10000000; //super big so first find is always smaller

        for (int i = 0; i < locations.size(); i++){
            float tempDistance = calculateDistanceBetween(current, locations.get(i));

            if (tempDistance < shortestDistance){
                shortestDistance = tempDistance;
                nearest = i;
            }
        }

        return nearest;
    }

    public Leg makeLeg(Location A, Location B, int sequence){
        int mileage = 0;
        mileage = calculateDistanceBetween(A,B); // get mileage between locations
        Leg leg = new Leg(sequence, mileage); //need to calculate mileage between
        leg.setStartLocation(A);
        leg.setEndLocation(B);
        return leg;
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

        return (int) distance;
    }

    public Location getLocation(int index){
        return locations.get(index);
    }

}
