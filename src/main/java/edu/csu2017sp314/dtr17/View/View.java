package main.java.edu.csu2017sp314.dtr17.View;

import main.java.edu.csu2017sp314.dtr17.Presenter.Presenter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.cli.*;

public class View implements ActionListener{
    private boolean showMileage = false;
    private boolean showID = false;
    private boolean showName = false;
    private String filename;
    private String filenameCut;

    private GUI gui;

    protected TripFileCreator tripFileCreator = new TripFileCreator();


    protected Presenter presenter;

    public View(String[] args) {
        if (CommandParser(args) == 0) {
            //the parser didn't throw an error
        } else {
            System.out.println("There was an error parsing the command line.");
        }

        this.presenter = presenter;
        gui = new GUI(this);
        gui.setSVGFilePath(filenameCut + ".svg");
        gui.startGUI();

    }

    public void addLocation(String name, String ID, double longitude, double latitude, int mileageToNextLoc) {
        tripFileCreator.addLocation(name, ID, longitude, latitude, mileageToNextLoc);

    }

    public void printFiles(){
        tripFileCreator.printSVGFile(filenameCut + ".svg", showID, showName, showMileage);
        tripFileCreator.printXMLFile(filenameCut + ".xml");

        gui.setItineraryStrings(tripFileCreator.getItineraryStrings());
    }

    public boolean getMileageFlag() {
        return showMileage;
    }

    public boolean getIDFlag() {
        return showID;
    }

    public boolean getNameFlag() {
        return showName;
    }

    public String getFilename() {
        return filename;
    }



    public void setPresenterReference(Presenter presenter){
        this.presenter = presenter;
    }

    /* parses the arguments from the command line and stores them locally */
    private int CommandParser(String[] args) {
        int argsLength = args.length;

        //checks if there is just the program name in arguments
        //if there is, it throws an error and requests the file name
        if (argsLength == 0) {
            System.out.println("The input file name must be included as an argument.");
            System.out.println("Correct usage of the command line is: TripCo.java -flags inputfile.csv");
            System.out.println("Recognized flags are:\n-m for adding mileages to the map\n-i for adding IDs to the map\n-n for adding Names to the map");
            return -1;
        }

        //checks if there is more than just the program name in arguments
        char flag;
        if (argsLength > 1) {
            //takes the next argument and saves it as the flags String
            for (int i = 0; i < argsLength - 1; i++) {
                String flags = args[i];
                //goes through each character in the flags String after the '-'
                for (int j = 1; j < flags.length(); j++) {
                    flag = flags.charAt(j);
                    //sets the appropriate boolean to true if the correct flag is present
                    switch (flag) {
                        case 'm':
                            showMileage = true;
                            break;
                        case 'i':
                            showID = true;
                            break;
                        case 'n':
                            showName = true;
                            break;
                        default:
                            System.out.println("The flag " + flags + " is not a valid flag!");
                            System.out.println("Correct usage of the command line is: TripCo.java -flags inputfile.csv");
                            System.out.println("Recognized flags are:\n-m for adding mileages to the map\n-i for adding IDs to the map\n-n for adding Names to the map");
                            break;
                    }
                }
            }
        }

        if (showID && showName) {
            System.out.println("Only the labels for ID or Name can be turned on, not both! The system will only show ID labels instead of both, if they are both flagged.");
        }

        //takes the last argument and saves it as the filename
        filename = (args[argsLength - 1]);
        filenameCut = (args[argsLength - 1].substring(0, (args[argsLength - 1].length() - 4)));

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

            presenter.createSVGButtonPressed(tmpOptionsList.getCheckedState(OptionsList.OPTIONS.TWO_OP),
                    tmpOptionsList.getCheckedState(OptionsList.OPTIONS.THREE_OP));
        }
    }

    public void displaySVG(){
        gui.displaySVG(filenameCut + ".svg");
    }

    public void updateItinerary(){
        gui.updateItinerary();
    }
}
