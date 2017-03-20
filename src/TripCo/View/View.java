package TripCo.View;

import java.io.PrintWriter;
import java.util.ArrayList;

public class View {
    private boolean showMileage;
    private boolean showID;
    private boolean showName;
    private String filename;
    private String filenameCut;
    private int totalMileage;
    private ArrayList<String> names;
    private ArrayList<String> IDs;
    private ArrayList<Integer> xList;
    private ArrayList<Integer> yList;
    private ArrayList<Integer> mileages;
    private double fileWidth = 1066.6073;
    private double fileHeight = 783.0824;
    private double XMin = 34.72952;
    private double XMax = 1027.6634;
    private double YMin = 34.76269;
    private double YMax = 744.70214;

    public View(String[] args) {
        showMileage = false;
        showID = false;
        showName = false;
        totalMileage = 0;
        names = new ArrayList<>();
        IDs = new ArrayList<>();
        xList = new ArrayList<>();
        yList = new ArrayList<>();
        mileages = new ArrayList<>();
        if (CommandParser(args) == 0) {
            //the parser didn't throw an error
        } else {
            System.out.println("There was an error parsing the command line.");
        }
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

        int pixLong = longToPix(longitude);
        xList.add(pixLong);

        int pixLat = latToPix(latitude);
        yList.add(pixLat);

        mileages.add(mileageToNextLoc);
    }

    protected void printXMLFile(){
        //creates the name of the xml file
        String xmlName = filenameCut + ".xml";

        //writes to the xml file
        try {
            PrintWriter writer = new PrintWriter(xmlName, "UTF-8");
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<trip>");

            for (int i = 0; i < mileages.size(); i++) {
                String secondName;
                if (i + 1 >= names.size()) {
                    secondName = names.get(0);
                } else {
                    secondName = names.get(i + 1);
                }

                writer.println("<leg>");
                writer.println("<sequence>" + (i + 1) + "</sequence>");
                writer.println("<start>" + names.get(i) + "</start>");
                writer.println("<finish>" + secondName + "</finish>");
                writer.println("<mileage>" + mileages.get(i) + "</mileage>");
                writer.println("</leg>");
            }
            writer.println("</trip>");
            writer.close();

        } catch (Exception e) {
            System.out.println("An error has occurred while writing to the xml files.");
            e.printStackTrace();
        }
    }

    protected void writeLegsToSVG(PrintWriter writer){
        //write the legs to the file
        writer.println("<g>");
        writer.println("<title>Legs</title>");

        for (int i = 0; i < mileages.size(); i++) {
            int secondY;
            if (i + 1 >= yList.size()) {
                secondY = yList.get(0);
            } else {
                secondY = yList.get(i + 1);
            }

            int secondX;
            if (i + 1 >= xList.size()) {
                secondX = xList.get(0);
            } else {
                secondX = xList.get(i + 1);
            }

            writer.println("<line id=\"leg1\" y2=\"" + secondY + "\" x2=\""
                    + secondX + "\" y1=\"" + yList.get(i) + "\" x1=\""
                    + xList.get(i) + "\" stroke-width=\"3\" stroke=\"#999999\"/>");
        }

        writer.println("</g>");
    }

    protected void writeTitlesToSVG(PrintWriter writer){
        double namePosition = ((YMin) / 2.0) + 7;
        double milPosition = ((fileHeight + YMax) / 2.0) + 7;
        double stateMid = (XMax) / 2.0;

        //write the titles to the file
        writer.println("<g>");
        writer.println("<title>Titles</title>");
        writer.println("<text text-anchor=\"middle\" font-family=\"Sans-serif\" font-size=\"24\" id=\"state\" y=\"" + namePosition + "\" x=\"" + stateMid + "\">Colorado</text>");
        writer.println("<text text-anchor=\"middle\" font-family=\"Sans-serif\" font-size=\"24\" id=\"distance\" y=\"" + milPosition + "\" x=\"" + stateMid + "\">" + totalMileage + " miles</text>");
        writer.println("</g>");
    }

    protected void writeLocationsToSVG(PrintWriter writer){
        //write the locations to the file
        writer.println("<g>");
        writer.println("<title>Locations</title>");
        if (showID) {
            for (int i = 0; i < IDs.size(); i++) {
                writer.println("<text font-family=\"Sans-serif\" font-size=\"16\" id=\"id1\" y=\"" + yList.get(i) + "\" x=\"" + xList.get(i) + "\">" + IDs.get(i) + "</text>");
            }
        } else if (showName) {
            for (int i = 0; i < names.size(); i++) {
                writer.println("<text font-family=\"Sans-serif\" font-size=\"16\" id=\"id1\" y=\"" + yList.get(i) + "\" x=\"" + xList.get(i) + "\">" + names.get(i) + "</text>");
            }
        }
        writer.println("</g>");
    }

    protected void writeMileagesToSVG(PrintWriter writer){
        //write the distances to the file
        writer.println("<g>");
        writer.println("<title>Distances</title>");
        for (int i = 0; i < mileages.size(); i++) {
            int secondX;
            if (i + 1 >= xList.size()) {
                secondX = xList.get(0);
            } else {
                secondX = xList.get(i + 1);
            }
            int secondY;
            if (i + 1 >= yList.size()) {
                secondY = yList.get(0);
            } else {
                secondY = yList.get(i + 1);
            }

            //set the mileage label's X value
            int milx = (xList.get(i) + secondX)/2;
            //set the mileage label's Y value
            int mily = (yList.get(i) + secondY)/2;

            writer.println("<text font-family=\"Sans-serif\" font-size=\"16\" id=\"leg1\" y=\"" + mily + "\" x=\"" + milx + "\">" + mileages.get(i) + "</text>");
        }
        writer.println("</g>");
    }

    public void printFiles() {
        printXMLFile();

        //creates the svg file
        String svgName = filenameCut + ".svg";

        //writes to the svg file
        try {
            PrintWriter writer = new PrintWriter(svgName, "UTF-8");
            writer.println("<?xml version=\"1.0\"?>");
            writer.println("<svg width=\""  + fileWidth + "\" height=\"" + fileHeight + "\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\">");

            //write the legs to the file
            writeLegsToSVG(writer);

            //write the Borders to the file
            writer.println(Borders());

            writeTitlesToSVG(writer);

            //check for the ID or Name flag
            if (showID || showName) {
                writeLocationsToSVG(writer);
            }

            //check for the Mileage flag
            if (showMileage) {
                writeMileagesToSVG(writer);
            }

            writer.println("</svg>");
            writer.close();

        } catch (Exception e) {
            System.out.println("An error has occurred while writing to the svg file.");
            e.printStackTrace();
        }
    }

    private int longToPix(double longitude) {
        double width = (XMax - XMin);
        double mid = (fileWidth / 2);
        longitude = Math.abs(longitude);
        longitude = 109 - longitude;
        longitude = longitude * width;
        longitude = longitude / 7;
        longitude = XMax - longitude;
        if(longitude > mid) {
            double deviation =  longitude - mid;
            longitude = mid - deviation;
        }
        else {
            double deviation = mid - longitude;
            longitude = deviation + mid;
        }
        int longPix = (int)(Math.rint(longitude));
        return longPix;
    }

    private int latToPix(double latitude) {
        double height = (YMax - YMin);
        double mid = (fileHeight / 2);
        latitude = Math.abs(latitude);
        latitude = 41 - latitude;
        latitude = latitude * height;
        latitude = latitude / 4;
        latitude = YMax - latitude;
        if(latitude > mid) {
            double deviation = latitude - mid;
            latitude = mid - deviation;
        }
        else {
            double deviation = mid - latitude;
            latitude = deviation + mid;
        }
        int latPix = (int)(Math.rint(latitude));
        return latPix;
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
        int x1 = longToPix(-109.0);
        int x2 = longToPix(-102.0);
        int y1 = latToPix(41.0);
        int y2 = latToPix(37.0);


        String borderString = "<g>\n" +
                "  <title>Borders</title>\n" +
                "  <line id=\"north\" y2=\"" + y1 + "\" x2=\"" + x2 + "\" y1=\"" + y1 + "\" x1=\"" + x1 + "\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                "  <line id=\"east\" y2=\"" + y2 + "\" x2=\"" + x2 + "\" y1=\"" + y1 + "\" x1=\"" + x2 + "\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                "  <line id=\"south\" y2=\"" + y2 + "\" x2=\"" + x1 + "\" y1=\"" + y2 + "\" x1=\"" + x2 + "\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                "  <line id=\"west\" y2=\"" + y1 + "\" x2=\"" + x1 + "\" y1=\"" + y2 + "\" x1=\"" + x1 + "\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                " </g>";

        return borderString;
    }
}
