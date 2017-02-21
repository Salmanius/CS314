package TripCo.View;

import java.util.ArrayList;

public class View {
    private boolean showMileage;
    private boolean showID;
    private boolean showName;
    private String filename;
    private int totalMileage;
    private ArrayList<String> names;
    private ArrayList<String> IDs;
    private ArrayList<Double> longitudes;
    private ArrayList<Double> latitudes;
    private ArrayList<Integer> mileages;
    private int numOfLocs;

    public View(String[] args) {
        showMileage = false;
        showID = false;
        showName = false;
        totalMileage = 0;
        numOfLocs = 0;
        if (CommandParser(args) == 0) {
            //the parser didn't throw an error
        }
        else {
            System.out.println("There was an error parsing the command line.");
        }
    }

    public void display(String text) {
        System.out.println(text);
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

    public void addLocation(String name, String ID, double longitude, double latitude, int mileageToNextLoc) {
        totalMileage += mileageToNextLoc;
        names.add(name);
        IDs.add(ID);
        longitudes.add(longitude);
        latitudes.add(latitude);
        mileages.add(mileageToNextLoc);
        numOfLocs++;
    }

    public void printFiles() {
        
    }

    /* parses the arguments from the command line and stores them locally */
    private int CommandParser(String[] args) {
        int argsLength = args.length;

        //checks if there is just the program name in arguments
        //if there is, it throws an error and requests the file name
        if (argsLength == 1) {
            System.out.println("The input file name must be included as an argument.");
            System.out.println("Correct usage of the command line is: TripCo.java -flags inputfile.csv");
            System.out.println("Recognized flags are:\n-m for adding mileages to the map\n-i for adding IDs to the map\n-n for adding Names to the map");
            return -1;
        }

        //checks if there is more than just the program name in arguments
        char flag;
        if (argsLength > 2) {
            //takes the next argument and saves it as the flags String
            for (int i = 1; i < argsLength - 1; i++) {
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

        //takes the last argument and saves it as the filename
        filename = args[argsLength - 1];
        return 0;
    }

    /* inserts the borders into the svg file */
    private String Borders() {
        /*makes for easily changing the four corners, if needed
        *The corners are:
        * (x1,y1)---------(x2,y1)
        *   |               |
        *   |               |
        *   |               |
        * (x1,y2)---------(x2,y2)
         */
        int x1 = 50;
        int x2 = 1230;
        int y1 = 50;
        int y2 = 974;


        String borderString = "<g>\n" +
                "  <title>Borders</title>\n" +
                "  <!-- The border outline should provide a minimum 50 pixel border, centered in the window -->\n" +
                "  <line id=\"north\" y2=\"" + y1 + "\" x2=\"" + x2 + "\" y1=\"" + y1 + "\" x1=\"" + x1 + "\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                "  <line id=\"east\" y2=\"" + y2 + "\" x2=\"" + x2 + "\" y1=\"" + y1 + "\" x1=\"" + x2 + "\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                "  <line id=\"south\" y2=\"" + y2 + "\" x2=\"" + x1 + "\" y1=\"" + y2 + "\" x1=\"" + x2 + "\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                "  <line id=\"west\" y2=\"" + y1 + "\" x2=\"" + x1 + "\" y1=\"" + y2 + "\" x1=\"" + x1 + "\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                " </g>";

        return borderString;
    }
}
