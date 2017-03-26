package main.java.edu.csu2017sp314.dtr17.Presenter;

import main.java.edu.csu2017sp314.dtr17.Model.Leg;
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
        String fileName = view.getFilename();
        model.parseCSV(fileName);
        Trip trip = model.getTrip(twoOP,threeOP);

        for(int i = 0; i < trip.amountOfLegs(); ++i){
            Leg leg = trip.getLeg(i);
            view.addLocation(leg.getStartLocation().getName(), leg.getStartLocation().getId() ,
                    leg.getStartLocation().getDblLongitude(), leg.getStartLocation().getDblLatitude(), leg.getMileage());
        }

        view.printFiles();

    }
}


