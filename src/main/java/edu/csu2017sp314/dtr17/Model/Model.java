package main.java.edu.csu2017sp314.dtr17.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mjdun on 2/16/2017.
 */
public class Model {
    protected ArrayList<Location> locations;

    public Model() {
        locations = new ArrayList<Location>();
    }

    public Trip getTrip(ArrayList<String> airportNames, boolean twoOptBool, boolean threeOptBool, boolean unit){
        locations.clear();
        DatabaseFetcher fetcher = new DatabaseFetcher();

        ArrayList<Airport> airports = fetcher.getAirportsFromAirportIDs(airportNames);
        for(int i = 0; i < airports.size(); ++i){
            String id = airports.get(i).getID();
            String name = airports.get(i).getName();
            String latitude = airports.get(i).getLatitude();
            String longitude = airports.get(i).getLongitude();

            locations.add(new Location(id, name, latitude, longitude));
        }
        TripMaker tm = new TripMaker(locations); //Need to pass the commandline args at some point
        Trip trip = tm.makeTrip(twoOptBool, threeOptBool, unit); //NN = Nearest Neighbor

        return trip;
    }

    public static void main(String[] args){

    }




}
