package main.java.edu.csu2017sp314.dtr17.View;

import main.java.edu.csu2017sp314.dtr17.Presenter.Presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.cli.*;

public class View implements ActionListener{
    protected boolean showMileage = false;
    protected boolean showID = false;
    protected boolean showName = false;
    protected boolean useGUI = false;
    protected boolean twoOpt = false;
    protected boolean threeOpt = false;
    protected boolean useBGMap = false;

    protected String csvFileName;
    protected String svgFileName;
    protected String xmlFileName;

    private GUI gui;

    protected TripFileCreator tripFileCreator = new TripFileCreator();


    protected Presenter presenter;

    public View(String[] args) {
        parseCommandArguments(args);

        this.presenter = presenter;

        if(useGUI) {
            gui = new GUI(this);
            gui.setSVGFilePath(svgFileName);
            gui.startGUI();
        }

    }

    public void addLocation(String name, String ID, double longitude, double latitude, int mileageToNextLoc) {
        tripFileCreator.addLocation(name, ID, longitude, latitude, mileageToNextLoc);

    }

    public void printFiles(){
        tripFileCreator.printSVGFile(svgFileName, showID, showName, showMileage, useBGMap);
        tripFileCreator.printXMLFile(xmlFileName);

        if(useGUI)
            gui.setItineraryStrings(tripFileCreator.getItineraryStrings());

        tripFileCreator.clear();
    }

    public boolean getMileageFlag() { return showMileage; }
    public boolean getIDFlag() {return showID;}
    public boolean getNameFlag() { return showName; }
    public boolean isTwoOpt(){return twoOpt;}
    public boolean isThreeOpt(){return threeOpt;}

    public String getFilename() {
        return csvFileName;
    }

    public void setPresenterReference(Presenter presenter){
        this.presenter = presenter;
    }

    /* parses the arguments from the command line and stores them locally */
    private int parseCommandArguments(String[] args) {
        Options options = new Options();

        Option guiOption = new Option("g", "gui", false, "run the trip GUI");
        guiOption.setRequired(false);
        options.addOption(guiOption);

        Option mileageOption = new Option("m", "mileage", false, "display the mileage of each leg");
        mileageOption.setRequired(false);
        options.addOption(mileageOption);

        Option idOption = new Option("i", "id", false, "display the id of each stop");
        idOption.setRequired(false);
        options.addOption(idOption);

        Option namesOption = new Option("n", "names", false, "display the names of each stop");
        namesOption.setRequired(false);
        options.addOption(namesOption);

        Option csvPathOption = new Option("p", "csvpath", true, "set the filename or path to the input csv file");
        csvPathOption.setRequired(true);
        options.addOption(csvPathOption);

        Option svgOutputOption = new Option("s", "svgname", true, "set the filename or path to the output svg file");
        svgOutputOption.setRequired(false);
        options.addOption(svgOutputOption);

        Option xmlPathOption = new Option("x", "xmlname", true, "set the filename or path to the output xml file");
        xmlPathOption.setRequired(false);
        options.addOption(xmlPathOption);

        Option twoOptOption = new Option("2", "twoOpt", false, "Use 2-Opt optimization for trip planning");
        twoOptOption.setRequired(false);
        options.addOption(twoOptOption);

        Option threeOptOption = new Option("3", "threeOpt", false, "Use 3-Opt optimization for trip planning");
        threeOptOption.setRequired(false);
        options.addOption(threeOptOption);

        Option backgroundMapOption = new Option("b", "bgmap", false, "Include Colorado background map in generated SVG file");
        backgroundMapOption.setRequired(false);
        options.addOption(backgroundMapOption);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("TripCo", options);

            System.exit(1);
            return 1;
        }

        showMileage = cmd.hasOption("mileage");
        showID = cmd.hasOption("id");
        showName = cmd.hasOption("names");
        useGUI = cmd.hasOption("gui");
        twoOpt = cmd.hasOption("twoOpt");
        threeOpt = cmd.hasOption("threeOpt");
        useBGMap = cmd.hasOption("bgmap");

        csvFileName = cmd.getOptionValue("csvpath");
        String filenameCut = csvFileName.substring(0, csvFileName.length() - 4);

        if(cmd.hasOption("svgname")){
            svgFileName = cmd.getOptionValue("svgname");
        } else{
            svgFileName = filenameCut + ".svg";
        }

        if(cmd.hasOption("xmlname")){
            xmlFileName = cmd.getOptionValue("xmlname");
        } else{
            xmlFileName = filenameCut + ".xml";
        }

        return 0;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        String action = e.getActionCommand();

        if(action.equals(GUI.CREATE_TRIP_ACTION)) {
            OptionsList tmpOptionsList = gui.getOptionsList();
            showID = tmpOptionsList.getCheckedState(OptionsList.OPTIONS.ID);
            showMileage = tmpOptionsList.getCheckedState(OptionsList.OPTIONS.MILEAGE);
            showName = tmpOptionsList.getCheckedState(OptionsList.OPTIONS.NAMES);
            useBGMap = tmpOptionsList.getCheckedState(OptionsList.OPTIONS.BACKGROUND_MAP);

            presenter.createSVGButtonPressed(tmpOptionsList.getCheckedState(OptionsList.OPTIONS.TWO_OP),
                    tmpOptionsList.getCheckedState(OptionsList.OPTIONS.THREE_OP));
        }
    }

    public void displaySVG(){
        gui.displaySVG(svgFileName);
    }

    public void updateItinerary(){
        gui.updateItinerary();
    }

    public boolean isGUIOn(){return useGUI;}
}
