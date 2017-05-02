package main.java.edu.csu2017sp314.dtr17.View;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by mjdun on 3/26/2017.
 */
public class TripFileCreator {
    private boolean showMileage;
    private boolean showID;
    private boolean showName;
    protected boolean useBGMap = false;
    private String filename;

    private int totalMileage;

    private ArrayList<String> names = new ArrayList<String>();
    private ArrayList<String> IDs = new ArrayList<String>();
    private ArrayList<Integer> xList = new ArrayList<Integer>();
    private ArrayList<Integer> yList = new ArrayList<Integer>();
    private ArrayList<Double> xRaw = new ArrayList<Double>();
    private ArrayList<Double> yRaw = new ArrayList<Double>();
    private ArrayList<Integer> mileages = new ArrayList<Integer>();

    public static final double FILE_SCALE = 4.0;
    public static final double RAW_WIDTH = 800;
    public static final double RAW_HEIGHT = 400;
    public static final double FILE_WIDTH = RAW_WIDTH*(FILE_SCALE/(RAW_WIDTH/360));
    public static final double FILE_HEIGHT = RAW_HEIGHT*(FILE_SCALE/(RAW_HEIGHT/180));

    protected String units;


    public static final String BACKGROUND_FILE_NAME = "World4.svg";

    private String[] itineraryStrings;

    public TripFileCreator(){}

    public int getTotalMileage(){return totalMileage;}

    public void setTotalMileage(int newMileage){totalMileage = newMileage;}

    public void clear(){
        names.clear();
        IDs.clear();
        xList.clear();
        yList.clear();
        mileages.clear();
        itineraryStrings = null;
        totalMileage = 0;
    }

    public void addLocation(String name, String ID, double longitude, double latitude, int mileageToNextLoc) {
        names.add(name);
        IDs.add(ID);
        xRaw.add(longitude);
        yRaw.add(latitude);

        int pixLong = longToPix(longitude);
        xList.add(pixLong);

        int pixLat = latToPix(latitude);
        yList.add(pixLat);

        mileages.add(mileageToNextLoc);
    }

    public int longToPix(double longitude) {
        longitude =  (180 + longitude)*FILE_SCALE;
        int longPix = (int)(Math.rint(longitude));
        return longPix;
    }

    public int latToPix(double latitude) {
        latitude = (90 - latitude)*FILE_SCALE;
        int latPix = (int)(Math.rint(latitude));
        return latPix;
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
            if (wrapAround(xList.get(i), secondX)) {
                int rx;
                int ry;
                int lx;
                int ly;

                if (xList.get(i) > secondX){
                    rx = xList.get(i);
                    ry = yList.get(i);
                    lx = secondX;
                    ly = secondY;
                }
                else {
                    rx = secondX;
                    ry = secondY;
                    lx = xList.get(i);
                    ly = yList.get(i);
                }
                //left line to left edge
                printLeftWrap(writer, lx, ly, rx, ry);
                //right line to right edge
                printRightWrap(writer, rx, ry, lx, ly);

            } else {
                writer.println("<line id=\"leg1\" y2=\"" + secondY + "\" x2=\""
                        + secondX + "\" y1=\"" + yList.get(i) + "\" x1=\""
                        + xList.get(i) + "\" stroke-width=\"1.7\" stroke=\"#999999\"/>");
            }
        }
        writer.println("</g>");
    }

    protected void writeTitlesToSVG(PrintWriter writer){
        double milePosition = (FILE_HEIGHT) - 20;
        double stateMid = ((FILE_WIDTH) / 2.0);

        //write the titles to the file
        writer.println("<g>");
        writer.println("<text text-anchor=\"middle\" font-family=\"Sans-serif\" font-size=\"24\" id=\"distance\" y=\""
                + milePosition + "\" x=\"" + stateMid + "\">" + totalMileage + " " + units + "</text>");
        writer.println("</g>");
    }

    protected void writeLocationsToSVG(PrintWriter writer){
        //write the locations to the file
        writer.println("<g>");
        writer.println("<title>Locations</title>");
        if (showID) {
            for (int i = 0; i < IDs.size(); i++) {
                writer.println("<text font-family=\"Sans-serif\" font-size=\"4\" id=\"id1\" y=\"" + yList.get(i) + "\" x=\"" + xList.get(i) + "\">" + IDs.get(i) + "</text>");
            }
        } else if (showName) {
            for (int i = 0; i < names.size(); i++) {
                writer.println("<text font-family=\"Sans-serif\" font-size=\"4\" id=\"id1\" y=\"" + yList.get(i) + "\" x=\"" + xList.get(i) + "\">" + names.get(i) + "</text>");
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

            if (wrapAround(xList.get(i), secondX)){ //ROUGH way to display miles on wrapped lines
                writer.println("<text font-family=\"Sans-serif\" font-size=\"16\" id=\"leg1\" y=\"" + secondY + "\" x=\"" + secondX + "\">" + mileages.get(i) + "</text>");
            }
            else {
                writer.println("<text font-family=\"Sans-serif\" font-size=\"16\" id=\"leg1\" y=\"" + mily + "\" x=\"" + milx + "\">" + mileages.get(i) + "</text>");
            }
        }
        writer.println("</g>");
    }

    protected void writeHeader(PrintWriter writer){
        writer.println("<?xml version=\"1.0\"?>");
        writer.println("<svg width=\""  + FILE_WIDTH + "\" height=\"" + FILE_HEIGHT + "\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:svg=\"http://www.w3.org/2000/svg\">");
    }

    protected void writeBackground(PrintWriter writer)
    {
        double matrixScale = FILE_SCALE/(RAW_WIDTH/360);
        File file = new File(BACKGROUND_FILE_NAME);
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line = br.readLine();
            writer.println(line);
            writer.println("<svg \n" +
                    "\txmlns:svg=\"http://www.w3.org/2000/svg\" \n" +
                    "\txmlns=\"http://www.w3.org/2000/svg\" \n" +
                    "\twidth=\"" + FILE_WIDTH + "\" height=\"" + FILE_HEIGHT + "\">\n" +
                    "<g transform=\"matrix(" + matrixScale + " 0 0 " + matrixScale + " 0 0)\">");
            while ((line = br.readLine()) != null) {
                writer.println("    " + line);
            }
            writer.println("</g>\n");
            br.close();

        } catch(IOException exception){
            System.out.println("Could not open file: " + BACKGROUND_FILE_NAME);
        }
    }
    //creates the svg file
    public void printSVGFile(String filename, boolean showID, boolean showName, boolean showMileage, String units) {

        this.showID = showID;
        this.showName = showName;
        this.showMileage = showMileage;
        this.useBGMap = useBGMap;
        this.units = units;

        String svgName = filename;

        //writes to the svg file
        try {
            PrintWriter writer = new PrintWriter(svgName, "UTF-8");

            writeBackground(writer);

            //write the legs to the file
            writeLegsToSVG(writer);


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

    protected void printXMLFile(String fileName){
        //creates the name of the xml file
        itineraryStrings = new String[mileages.size()];
        //writes to the xml file
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            writer.println("<trip>");

            for (int i = 0; i < mileages.size(); i++) {
                String secondName;
                String itineraryString = "";
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

                //data pulled from xml for itinerary
                itineraryString = itineraryString
                        + (i + 1) + " "
                        + names.get(i) + " to "
                        + secondName + ", "
                        + mileages.get(i) + " miles";
                itineraryStrings[i] = itineraryString;
            }
            writer.println("</trip>");
            writer.close();

        } catch (Exception e) {
            System.out.println("An error has occurred while writing to the xml files.");
            e.printStackTrace();
        }
    }

    protected void printHTMLFile(String fileName){
        //creates the name of the xml file
        itineraryStrings = new String[mileages.size()];
        //writes to the xml file
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println("<!DOCTYPE html>\n <html>\n<head>\n<meta charset='UTF-8'>\n<title>HTML Itinerary</title>\n" +
		    "</head>\n<body>\n<table border=1>\n <thead>\n <tr>\n<th>sequence</th>\n<th>start</th>\n<th>finish</th>\n<th>mileage</th>\n" +
			"</tr>\n</thead>\n<tbody>\n");

            /*<tr>
                <td>1</td>
                <td>Kaufana Airport</td>
				<td>Greenthorpe Airport</td>
                <td>2389</td>
            </tr>*/

            for (int i = 0; i < mileages.size(); i++) {
                String secondName;
                String itineraryString = "";
                if (i + 1 >= names.size()) {
                    secondName = names.get(0);
                } else {
                    secondName = names.get(i + 1);
                }

                writer.println("<tr>");
                writer.println("<td>" + (i + 1) + "</td>");
                writer.println("<td>" + names.get(i) + "</td>");
                writer.println("<td>" + secondName + "</td>");
                writer.println("<td>" + mileages.get(i) + "</td>");
                writer.println("</tr>");

                //data pulled from xml for itinerary
                itineraryString = itineraryString
                        + (i + 1) + " "
                        + names.get(i) + " to "
                        + secondName + ", "
                        + mileages.get(i) + " miles";
                itineraryStrings[i] = itineraryString;
            }
            writer.println("</tbody>\n</table>\n</body>\n</html>");

        } catch (Exception e) {
            System.out.println("An error has occurred while writing to the html files.");
            e.printStackTrace();
        }
    }

    protected void printKMLFile(String fileName){
        //creates the name of the kml file
        //writes to the kml file
        try {
            PrintWriter writer = new PrintWriter(fileName, "UTF-8");
            writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");  //required part of xml style files
            writer.println("<kml xmlns=\"http://www.opengis.net/kml/2.2\">"); //required for kml files
            writer.println("    <Document>");
            writer.println("        <name>"+"Generated Map"+"</name>");
            writer.println("        <Style id=\"transBluePoly\">\n" +
                    "         <LineStyle>\n" +
                    "            <width>1.5</width>\n" +
                    "        </LineStyle>\n" +
                    "        <PolyStyle>\n" +
                    "         <color>7dff0000</color>\n" +
                    "        </PolyStyle>\n" +
                    "        </Style>");

            writer.println("     <Placemark>");
            writer.println("        <LineString>");
            writer.print("          <coordinates>");
            for (int i = 0; i < mileages.size(); i++){
                writer.print(xRaw.get(i)+","+yRaw.get(i)+","+'0'+" ");
            }
            writer.print(xRaw.get(0)+","+yRaw.get(0)+","+'0');
            writer.print("</coordinates>");
            writer.println("        </LineString>");
            writer.println("     </Placemark>");
            for (int i = 0; i < mileages.size(); i++){
                writer.println("     <Placemark>");
                writer.println("            <name>"+names.get(i)+"</name>"); //ADD THE NAME OF THE POINT
                writer.println("            <description>"+IDs.get(i)+"</description>"); //ADD NAME OF DESCRIPTION IF NEEDED
                writer.println("           <Point>");
                writer.println("               <coordinates>"+xRaw.get(i)+","+yRaw.get(i)+","+'0'+"</coordinates>"); //lat,long,altitude
                writer.println("           </Point>");
                writer.println("     </Placemark>");
            }
            writer.println("    </Document>");
            writer.println("</kml>");
            writer.close();

        } catch (Exception e) {
            System.out.println("An error has occurred while writing to the kml files.");
            e.printStackTrace();
        }
    }


    public boolean wrapAround(int x1, int x2){
        int half = (int)FILE_WIDTH/2;
        if ((Math.abs(x1 - x2)) > half){
            return true;
        }
        return false;
    }

    public void printRightWrap(PrintWriter writer, int rx, int ry, int lx, int ly){
        writer.println("<line id=\"leg1\" y2=\"" + ry + "\" x2=\""
                + rx + "\" y1=\"" + ly + "\" x1=\""
                + (FILE_WIDTH+lx) + "\" stroke-width=\"1.7\" stroke=\"#999999\"/>");
    }

    public void printLeftWrap(PrintWriter writer, int lx, int ly, int rx, int ry){
        writer.println("<line id=\"leg1\" y2=\"" + ly + "\" x2=\""
                + lx + "\" y1=\"" + ry + "\" x1=\""
                + (0-(FILE_WIDTH-rx)) + "\" stroke-width=\"1.7\" stroke=\"#999999\"/>");
    }

    public String[] getItineraryStrings(){
        return itineraryStrings;
    }

}
