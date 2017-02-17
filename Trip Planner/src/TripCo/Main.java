package TripCo;
import TripCo.Presenter.Presenter;
import TripCo.View.View;
import TripCo.Model.Model;

public class Main {

    public static void main(String[] args) {
	    // write your code here
        // argument handling?
        Model model = new Model();
        View view = new View();
        Presenter presenter = new Presenter(view, model);
        presenter.start();
    }
}
