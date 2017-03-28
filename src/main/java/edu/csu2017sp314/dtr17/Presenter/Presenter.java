package main.java.edu.csu2017sp314.dtr17.Presenter;

import main.java.edu.csu2017sp314.dtr17.Model.Location;
import main.java.edu.csu2017sp314.dtr17.Model.Model;
import main.java.edu.csu2017sp314.dtr17.Model.Trip;
import main.java.edu.csu2017sp314.dtr17.View.View;

/**
 * Created by mjdun on 2/13/2017.
 */
public class Presenter {
    private View view;
    private Model model;
    private boolean oneOP;
    private boolean twoOP;
    private boolean threeOP;


    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void start() {
        if(!view.isGUIOn()) {
            String fileName = view.getFilename();
            model.parseCSV(fileName);
            Trip trip = model.getTrip(twoOP, threeOP);

            //pass data to the View
            for (int i = 0; i < trip.getSize(); i++) {
                Location loc = trip.getLoc(i);
                view.addLocation(loc.getName(), loc.getId(),
                        loc.getDblLongitude(), loc.getDblLatitude(), trip.getLegMileage(i));
            }

            view.printFiles();
        }

    }

    public void createSVGButtonPressed(boolean twoOP, boolean threeOP){
        String fileName = view.getFilename();
        model.parseCSV(fileName);
        Trip trip = model.getTrip(twoOP,threeOP);

        for(int i = 0; i < trip.getSize(); ++i){
            Location loc = trip.getLoc(i);
            view.addLocation(loc.getName(), loc.getId() ,
                    loc.getDblLongitude(), loc.getDblLatitude(), trip.getLegMileage(i));
        }

        view.printFiles();
        view.displaySVG();
        view.updateItinerary();
    }
}


