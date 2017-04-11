package main.java.edu.csu2017sp314.dtr17.Model;

import java.util.ArrayList;

/**
 * Created by mjdun on 2/16/2017.
 */
public class Model {
    protected ArrayList<Location> locations;

    public Model() {
        locations = new ArrayList<Location>();
    }

    /*public void parseCSV(String fileName){
        locations.clear();
        CSVParser parser = new CSVParser(fileName);
        parser.parse();
        //parser.printData();

        CSVData csvData = parser.getCSVData();
        //System.out.println(csvData.getHeight());
        for(int i = 0; i < csvData.getHeight(); ++i){
            //System.out.println(i);
            String id = csvData.getValue("id", i);
            String name = csvData.getValue("name", i);
            String latitude = csvData.getValue("latitude", i);
            String longitude = csvData.getValue("longitude", i);

            locations.add(new Location(id, name, latitude, longitude));
        }

    } */

    public Trip getTrip(ArrayList<String> airportNames, boolean twoOptBool, boolean threeOptBool, boolean unit){
        locations.clear();
        DatabaseFetcher fetcher = new DatabaseFetcher();
        for(int i = 0; i < airportNames.size(); ++i){
            String id = fetcher.getAirportIDFromName(airportNames.get(i));
            String name = airportNames.get(i);
            String latitude = fetcher.getLatitudeFromName(airportNames.get(i));
            String longitude = fetcher.getLongitudeFromName(airportNames.get(i));

            locations.add(new Location(id, name, latitude, longitude));
        }

        TripMaker tm = new TripMaker(locations); //Need to pass the commandline args at some point
        Trip trip = tm.makeTrip(twoOptBool, threeOptBool, unit); //NN = Nearest Neighbor

        return trip;
    }

    public static void main(String[] args){

    }




}
