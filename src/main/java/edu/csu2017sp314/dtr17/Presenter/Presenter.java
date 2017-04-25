package main.java.edu.csu2017sp314.dtr17.Presenter;

import main.java.edu.csu2017sp314.dtr17.Model.Location;
import main.java.edu.csu2017sp314.dtr17.Model.Model;
import main.java.edu.csu2017sp314.dtr17.Model.Trip;
import main.java.edu.csu2017sp314.dtr17.View.GUIController;
import main.java.edu.csu2017sp314.dtr17.View.View;

import java.util.ArrayList;
import java.util.StringJoiner;

/**
 * Created by mjdun on 2/13/2017.
 */
public class Presenter {
    private View view;
    private Model model;
    private boolean twoOP = false;
    private boolean threeOP = false;
    //False is Miles, True is km
    private boolean units = false;
    private GUIController guiController;


    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void start() {
    }

    public void setGuiController(GUIController guiController){
        this.guiController = guiController;
    }

    public void createSVGButtonPressed(ArrayList<String> airportNames, boolean twoOP, boolean threeOP, boolean units,
                                       boolean showID, boolean showDistance, boolean showNames ){

        Trip trip = model.getTrip(airportNames, twoOP,threeOP, units);

        //pass data to the View
        for(int i = 0; i < trip.getSize() -1; i++){
            Location loc = trip.getLoc(i);

            view.addLocation(loc.getName(), loc.getId() ,
                    loc.getDblLongitude(), loc.getDblLatitude(), 0); //THIS HAS GOTTA BE UPDATED!!!!!!!!!!!!!
        }
        view.setTotalMileage(trip.getTotalMileage());

        String svgFile = view.printFiles(showID, showDistance, showNames);

        view.updateItinerary();
        guiController.displaySVG(svgFile);
    }
}


