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

    public void parseCSV(String fileName){
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

    }

    public ArrayList<String> parseXML(String fileName){
        File file = new File(fileName);
        String idString = "<id>";
        String endString = "</id>";
        ArrayList<String> IDs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            if(line != null){
                if(line.toLowerCase().contains(idString.toLowerCase())){
                    String result = line.toLowerCase().substring(line.toLowerCase().indexOf(idString.toLowerCase()) + 4, line.toLowerCase().indexOf(endString.toLowerCase()));
                    IDs.add(result);
                }
            }

            while ((line = br.readLine()) != null) {
                if(line.toLowerCase().contains(idString.toLowerCase())){
                    String result = line.toLowerCase().substring(line.toLowerCase().indexOf(idString.toLowerCase()) + 4, line.toLowerCase().indexOf(endString.toLowerCase()));
                    IDs.add(result);
                }
            }

            br.close();
            return IDs;

        } catch(IOException exception){
            System.out.println("Could not open file: " + fileName);
            return null;
        }
    }

    public Trip getTrip(boolean twoOptBool, boolean threeOptBool, boolean unit){
        TripMaker tm = new TripMaker(locations); //Need to pass the commandline args at some point
        Trip trip = tm.makeTrip(twoOptBool, threeOptBool, unit); //NN = Nearest Neighbor

        return trip;
    }

    public static void main(String[] args){

    }




}
