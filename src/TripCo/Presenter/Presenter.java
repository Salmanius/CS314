package TripCo.Presenter;

import TripCo.Model.Leg;
import TripCo.Model.Model;
import TripCo.Model.Trip;
import TripCo.View.View;

/**
 * Created by mjdun on 2/13/2017.
 */
public class Presenter {
    private View view;
    private Model model;

    public Presenter(View view, Model model) {
        this.view = view;
        this.model = model;
    }
    public void start() {
        String fileName = view.getFilename();
        model.parseCSV(fileName);
        Trip trip = model.getTrip();

        for(int i = 0; i < trip.amountOfLegs(); ++i){
            Leg leg = trip.getLeg(i);
            view.addLocation(leg.getStartLocation().getName(), leg.getStartLocation().getId() ,
                    leg.getStartLocation().getDblLongitude(), leg.getStartLocation().getDblLatitude(), leg.getMileage());
        }
        view.printFiles();
    }
}


