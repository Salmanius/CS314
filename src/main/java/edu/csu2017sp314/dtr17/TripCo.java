package main.java.edu.csu2017sp314.dtr17;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.edu.csu2017sp314.dtr17.Presenter.Presenter;
import main.java.edu.csu2017sp314.dtr17.View.GUIController;
import main.java.edu.csu2017sp314.dtr17.View.View;
import main.java.edu.csu2017sp314.dtr17.Model.Model;

public class TripCo extends Application{
    protected Model model;
    protected View view;
    protected Presenter presenter;

    protected static String[] args;

    public static void main(String[] args) {
        // write your code here
        Model model = new Model();
        View view = new View(args);
        Presenter presenter = new Presenter(view, model);

        if(view.isGUIOn()){
            //if the GUI option has been indicated, launch the GUI.
            TripCo tripCo = new TripCo();
            tripCo.setArgs(args);
            launch(args);
        }else{
            presenter.start();
        }
    }

    public void setArgs(String[] args){
        this.args = args;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent root = fxmlLoader.load(getClass().getResource("View/mainGUI.fxml").openStream());
        Scene scene = new Scene(root, primaryStage.getMaxWidth(), primaryStage.getMaxHeight());
        primaryStage.setTitle("TripCo");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();

        model = new Model();
        view = new View(args);
        presenter = new Presenter(view, model);

        presenter.start();

        GUIController guiController = (GUIController) fxmlLoader.getController();
        guiController.setPresenter(presenter);
        presenter.setGuiController(guiController);
    }
}

