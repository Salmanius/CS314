package main.java.edu.csu2017sp314.dtr17.View;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import main.java.edu.csu2017sp314.dtr17.Presenter.Presenter;

import java.io.File;
import java.net.MalformedURLException;

/**
 * Created by mjdun on 4/1/2017.
 */
public class GUIController {
    public WebView SVGWebView;
    public ComboBox airportTypePicker;
    public ComboBox continentPicker;
    public ComboBox countryPicker;
    public ComboBox regionPicker;
    public TextField airportNameSearchBox;
    public Button searchButton;
    public ListView selectionListBox;
    public Button selectButton;
    public ListView selectedListBox;
    public Button clearButton;
    public Button loadButton;
    public Button saveButton;
    public ComboBox unitPicker;
    public ComboBox optimizationPicker;
    public CheckBox idCheckBox;
    public CheckBox distanceCheckBox;
    public CheckBox namesCheckBox;
    public Button showMapButton;

    protected Presenter presenter;

    public GUIController(){
        System.out.println("controller constructor called");
    }

    public void displaySVG(String filePath) {
        String url = "";
        try {
            url = new File(filePath).toURI().toURL().toString();
        }catch (MalformedURLException exception){
            url = "";
        }
        SVGWebView.getEngine().load(url);
    }

    public void loadMapPressed(ActionEvent actionEvent) {
        presenter.createSVGButtonPressed(false, false);
    }

    public void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }

    public void airportChanged(ActionEvent actionEvent) {
    }

    public void continentChanged(ActionEvent actionEvent) {
    }

    public void countryChanged(ActionEvent actionEvent) {
    }

    public void regionChanged(ActionEvent actionEvent) {
    }

    public void municipalityChanged(ActionEvent actionEvent) {
    }

    public void searchButtonPressed(ActionEvent actionEvent) {
    }

    public void selectButtonPressed(ActionEvent actionEvent) {
    }

    public void clearButtonPressed(ActionEvent actionEvent) {
    }

    public void loadButtonPressed(ActionEvent actionEvent) {
    }

    public void saveButtonPressed(ActionEvent actionEvent) {
    }

    public void unitsChanged(ActionEvent actionEvent) {
    }

    public void optimizationChanged(ActionEvent actionEvent) {
    }

    public void idOptionChanged(ActionEvent actionEvent) {
    }

    public void distanceOptionChanged(ActionEvent actionEvent) {
    }

    public void namesOptionChanged(ActionEvent actionEvent) {
    }

    public void showMapButtonPressed(ActionEvent actionEvent) {
    }
}
