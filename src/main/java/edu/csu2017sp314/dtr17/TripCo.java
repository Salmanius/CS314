package main.java.edu.csu2017sp314.dtr17;

import main.java.edu.csu2017sp314.dtr17.Presenter.Presenter;
import main.java.edu.csu2017sp314.dtr17.View.View;
import main.java.edu.csu2017sp314.dtr17.Model.Model;

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

