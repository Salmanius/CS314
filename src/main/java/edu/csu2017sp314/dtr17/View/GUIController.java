package main.java.edu.csu2017sp314.dtr17.View;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import main.java.edu.csu2017sp314.dtr17.Model.DatabaseFetcher;
import main.java.edu.csu2017sp314.dtr17.Presenter.Presenter;

import java.io.File;
import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by mjdun on 4/1/2017.
 */
public class GUIController {
    public WebView SVGWebView;

    public ComboBox airportTypePicker;
    public ComboBox continentPicker;
    public ComboBox countryPicker;
    public ComboBox regionPicker;
    public ComboBox municipalityPicker;

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
    protected DatabaseFetcher fetcher;

    public GUIController(){
        fetcher = new DatabaseFetcher();
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
        if(!continentPicker.getSelectionModel().isEmpty()) {
            String value = continentPicker.getValue().toString();

            List<String> countries = fetcher.getAllCountriesInContinent(fetcher.getContinentCodeFromName(value));

            resetComboBox(countryPicker);
            resetComboBox(regionPicker);
            resetComboBox(municipalityPicker);
            countryPicker.getItems().addAll(countries);
            countryPicker.setDisable(false);
        }
    }

    protected void resetComboBox(ComboBox comboBox){
        if(!comboBox.getSelectionModel().isEmpty()) {
            comboBox.getSelectionModel().clearSelection();
        }
        comboBox.getItems().clear();
        comboBox.setDisable(true);
    }

    protected void resetSearchFields(){
        continentPicker.getSelectionModel().clearSelection();

        resetComboBox(countryPicker);
        resetComboBox(regionPicker);
        resetComboBox(municipalityPicker);

    }

    public void countryChanged(ActionEvent actionEvent) {
        if(!countryPicker.getSelectionModel().isEmpty()) {
            String value = countryPicker.getValue().toString();

            List<String> regions = fetcher.getAllRegionsInISOCountry(fetcher.getCountryCodeFromName(value));

            resetComboBox(regionPicker);
            regionPicker.getItems().addAll(regions);
            regionPicker.setDisable(false);
        }
    }

    public void regionChanged(ActionEvent actionEvent) {
        if(!regionPicker.getSelectionModel().isEmpty()) {
            String value = regionPicker.getValue().toString();

            List<String> municipalities = fetcher.getAllMunicipalitiesInISORegion(fetcher.getISORegionFromName(value));

            resetComboBox(municipalityPicker);
            municipalityPicker.getItems().addAll(municipalities);
            municipalityPicker.setDisable(false);
        }
    }

    public void municipalityChanged(ActionEvent actionEvent) {
    }

    public void searchButtonPressed(ActionEvent actionEvent) {
        //resetSearchFields();
    }

    public void selectButtonPressed(ActionEvent actionEvent) {
    }

    public void clearButtonPressed(ActionEvent actionEvent) {
    }

    public void loadButtonPressed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Trip");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("csv", "*.csv"));

        File file = fileChooser.showOpenDialog(new Stage());

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
