package TripCo;

import TripCo.Presenter.Presenter;
import TripCo.View.View;
import TripCo.Model.Model;

public class TripCo {

    public static void main(String[] args) {
        // write your code here
        Model model = new Model();
        View view = new View(args);
        Presenter presenter = new Presenter(view, model);
        view.setPresenterReference(presenter);
        presenter.start();
    }
}

