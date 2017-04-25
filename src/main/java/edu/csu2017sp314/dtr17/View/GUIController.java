package main.java.edu.csu2017sp314.dtr17.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.web.PopupFeatures;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import main.java.edu.csu2017sp314.dtr17.Model.Airport;
import main.java.edu.csu2017sp314.dtr17.Model.DatabaseFetcher;
import main.java.edu.csu2017sp314.dtr17.Model.XMLParser;
import main.java.edu.csu2017sp314.dtr17.Presenter.Presenter;

import java.beans.XMLDecoder;
import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
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

    protected String selectedContinentID = "";
    protected String selectedISOCountry = "";
    protected String selectedISORegion = "";
    protected String selectedMunicipality = "";
    protected String selectedAirportType = "";


    protected Presenter presenter;
    protected DatabaseFetcher fetcher;

    protected ArrayList<String> airportNames;

    public GUIController(){
        fetcher = new DatabaseFetcher();
        airportNames = new ArrayList<String>();
    }

    public void displaySVG(String filePath) {
        String url = "";
        try {
            url = new File(filePath).toURI().toURL().toString();
        }catch (MalformedURLException exception){
            url = "";
        }
        //SVGWebView.getEngine().load(url);

        WebView wv = new WebView();
        wv.getEngine().setCreatePopupHandler(new Callback<PopupFeatures, WebEngine>() {

            @Override
            public WebEngine call(PopupFeatures p) {
                Stage stage = new Stage(StageStyle.UTILITY);
                WebView wv2 = new WebView();
                stage.setScene(new Scene(wv2, 2000, 1000));
                stage.show();
                stage.setMaximized(true);
                return wv2.getEngine();
            }
        });


        StackPane root = new StackPane();
        root.getChildren().add(wv);

        Scene scene = new Scene(root, 300, 250);

        final Stage stage = new Stage();
        stage.setTitle("Trip Map");
        stage.setScene(scene);
        stage.show();
        wv.getEngine().load(url);
    }

    public void loadMapPressed(ActionEvent actionEvent) {
        //presenter.createSVGButtonPressed(false, false);
    }

    public void setPresenter(Presenter presenter){
        this.presenter = presenter;
    }

    public void airportTypeChanged(ActionEvent actionEvent) {
        if(!airportTypePicker.getSelectionModel().isEmpty()) {
            String value = airportTypePicker.getValue().toString();

            if (airportTypePicker.getValue().toString().equals("Large Airport")) {
                selectedAirportType = "large_airport";
            } else if (value.equals("Medium Airport")) {
                selectedAirportType = "medium_airport";
            } else if (value.equals("Small Airport")) {
                selectedAirportType = "small_airport";
            } else if (value.equals("Seaplane Base")) {
                selectedAirportType = "seaplane_base";
            } else {
                selectedAirportType = value;
            }
        }
    }

    public void continentChanged(ActionEvent actionEvent) {
        if(!continentPicker.getSelectionModel().isEmpty()) {
            String value = continentPicker.getValue().toString();

            selectedContinentID = fetcher.getContinentCodeFromName(value);

            List<String> countries = fetcher.getAllCountriesInContinent(selectedContinentID);

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
        selectedISOCountry = "";
        if(!countryPicker.getSelectionModel().isEmpty()) {
            String value = countryPicker.getValue().toString();

            selectedISOCountry = fetcher.getCountryCodeFromName(value);
            List<String> regions = fetcher.getAllRegionsInISOCountry(selectedISOCountry);

            resetComboBox(regionPicker);
            regionPicker.getItems().addAll(regions);
            regionPicker.setDisable(false);
        }
    }

    public void regionChanged(ActionEvent actionEvent) {
        selectedISORegion = "";
        if(!regionPicker.getSelectionModel().isEmpty()) {
            String value = regionPicker.getValue().toString();

            selectedISORegion = fetcher.getISORegionFromName(value);
            List<String> municipalities = fetcher.getAllMunicipalitiesInISORegion(selectedISORegion);

            resetComboBox(municipalityPicker);
            municipalityPicker.getItems().addAll(municipalities);
            municipalityPicker.setDisable(false);
        }
    }

    public void municipalityChanged(ActionEvent actionEvent) {
        if(!municipalityPicker.getSelectionModel().isEmpty()){
            selectedMunicipality = municipalityPicker.getValue().toString();
        }

    }

    public void searchButtonPressed(ActionEvent actionEvent) {
        //resetSearchFields();
        selectionListBox.getItems().clear();

        //String query = "select id,name, municipality,iso_country,type from airports where " +
        //        "continent = '" + continentID + "' and iso_country = '" + isoCountry +
        //        "' and iso_region = '" + isoRegion + "' and municipality = '" + municipality + "'";

        String sqlColumnSpecifier = "";
        boolean isFirst = true;

        if(!selectedContinentID.isEmpty()){
            sqlColumnSpecifier += "continent = '" + selectedContinentID + "'";
            isFirst = false;
        }

        if(!selectedISOCountry.isEmpty()){
            if(isFirst)
                sqlColumnSpecifier += " iso_country = '" + selectedISOCountry + "'";
            else
                sqlColumnSpecifier += " and iso_country = '" + selectedISOCountry + "'";

            isFirst = false;
        }

        if(!selectedISORegion.isEmpty()){
            if(isFirst)
                sqlColumnSpecifier += " iso_region = '" + selectedISORegion + "'";
            else
                sqlColumnSpecifier += " and iso_region = '" + selectedISORegion + "'";

            isFirst = false;
        }

        if(!selectedMunicipality.isEmpty()){
            if(isFirst)
                sqlColumnSpecifier += " municipality = '" + selectedMunicipality + "'";
            else
                sqlColumnSpecifier += " and municipality = '" + selectedMunicipality + "'";

            isFirst = false;
        }

        if(!selectedAirportType.isEmpty()){
            if(isFirst)
                sqlColumnSpecifier += " type = '" + selectedAirportType + "'";
            else
                sqlColumnSpecifier += " and type = '" + selectedAirportType + "'";

            isFirst = false;
        }

        if(!airportNameSearchBox.getText().isEmpty()){
            if(isFirst)
                sqlColumnSpecifier += " name like '%" + airportNameSearchBox.getText().toString() + "%'";
            else
                sqlColumnSpecifier += " and name like '%" + airportNameSearchBox.getText().toString() + "%'";
        }

        if(sqlColumnSpecifier.isEmpty())
            sqlColumnSpecifier = " 1 = 1 ";

        ArrayList<String> airports = fetcher.searchForAirports(sqlColumnSpecifier);

        selectionListBox.getItems().addAll(airports);

    }

    public void selectButtonPressed(ActionEvent actionEvent) {
        selectedListBox.getItems().add(selectionListBox.getSelectionModel().getSelectedItem().toString());
        airportNames.add(fetcher.getAirportIDFromName(selectionListBox.getSelectionModel().getSelectedItem().toString()));
    }

    public void clearButtonPressed(ActionEvent actionEvent) {
        selectedListBox.getItems().clear();
        airportNames.clear();
    }

    public void loadButtonPressed(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Trip");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("xml", "*.xml"));

        File file = fileChooser.showOpenDialog(new Stage());

        XMLParser parser = new XMLParser();

        ArrayList<String> loadedIDs = parser.parseXML(file.getAbsolutePath());
        selectedListBox.getItems().clear();
        airportNames.clear();

        ArrayList<Airport> airports = fetcher.getAirportsFromAirportIDs(loadedIDs);

        for(int i = 0; i < airports.size(); ++i){
            airportNames.add(loadedIDs.get(i));
            selectedListBox.getItems().add(airports.get(i).getName());
        }

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
        boolean twoOp = false;
        boolean threeOp = false;
        if(optimizationPicker.getSelectionModel().isEmpty()){
            twoOp = false;
            threeOp = false;
        } else{
            if(optimizationPicker.getValue().toString().equals("Two-Op")){
                twoOp = true;
            }

            else if(optimizationPicker.getValue().toString().equals("Three-Op")){
                threeOp = true;
            }
        }

        boolean units = false;
        if(unitPicker.getSelectionModel().isEmpty()){
            units = false;
        } else {
            if(unitPicker.getValue().toString().equals("Miles")){
                units = false;
            } else if (unitPicker.getValue().toString().equals("Kilometers")){
                units = true;
            }
        }

        presenter.createSVGButtonPressed(airportNames, twoOp,threeOp, units, idCheckBox.isSelected(),
                distanceCheckBox.isSelected(), namesCheckBox.isSelected());
    }
}
