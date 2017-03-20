package TripCo.Model;

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
        CSVParser parser = new CSVParser(fileName);
        parser.parse();
       // parser.printData();

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

    public Trip getTrip(boolean twoOptBool, boolean threeOptBool){
        TripMaker tm = new TripMaker(locations); //Need to pass the commandline args at some point
        Trip NNTrip = tm.makeNNTrip(); //NN = Nearest Neighbor

        if(threeOptBool){
            if(locations.size() > 6){

            }
            else{
                System.out.println("This trip cannot be improved using 3opt because it has less than 6 locations.");
            }
        }
        else if(twoOptBool){
            if(locations.size() > 4) {
                int improvements = 1;
                while (improvements != 0) {
                    improvements = 0;
                    int best_mileage = NNTrip.getMileage();
                    for (int i = 0; i < locations.size() - 1; i++) {
                        for (int k = i + 1; k < locations.size(); k++) {
                            Trip newTrip = twoSwap(NNTrip, i, k);
                            int new_mileage = newTrip.getMileage();
                            if(new_mileage < best_mileage){
                                NNTrip = newTrip;
                                improvements++;
                            }
                        }
                    }
                }
            }
            else {
                System.out.println("This trip cannot be improved using 2opt because it has less than 4 locations.");
            }
        }

        return NNTrip;
    }

    public Trip twoSwap(Trip existing_route, int i,int k){
        Trip newTrip = existing_route;
        //get leg from existing_route and swap it
        return newTrip;
    }

    public static void main(String[] args){

    }




}
