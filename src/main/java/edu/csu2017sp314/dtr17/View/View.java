package main.java.edu.csu2017sp314.dtr17.View;

import main.java.edu.csu2017sp314.dtr17.Presenter.Presenter;

import org.apache.commons.cli.*;

public class View {
    protected boolean showMileage = false;
    protected boolean showID = false;
    protected boolean showName = false;
    protected boolean useGUI = false;
    protected boolean twoOpt = false;
    protected boolean threeOpt = false;
    protected boolean useBGMap = true;

    protected String csvFileName;
    protected String svgFileName = "map.svg";
    protected String xmlFileName = "itinerary.xml";
    protected String kmlFileName = "trip.kml";
    protected String htmlFileName = "itinerary.html";

    protected int totalMileage;

    protected TripFileCreator tripFileCreator = new TripFileCreator();


    protected Presenter presenter;

    public View(String[] args) {
        parseCommandArguments(args);
    }

    public void setTotalMileage(int newMileage){totalMileage = newMileage;}

    public void addLocation(String name, String ID, double longitude, double latitude, int mileageToNextLoc) {
        tripFileCreator.addLocation(name, ID, longitude, latitude, mileageToNextLoc);
    }

    public String printFiles(boolean showID, boolean showName, boolean showMileage, String units) {
        tripFileCreator.setTotalMileage(totalMileage);
        tripFileCreator.printSVGFile(svgFileName, showID, showName, showMileage, units);
        tripFileCreator.printXMLFile(xmlFileName);
        tripFileCreator.printHTMLFile(htmlFileName);
        tripFileCreator.printKMLFile(kmlFileName);

        tripFileCreator.clear();

        return svgFileName;
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

    public boolean isTwoOpt() {
        return twoOpt;
    }

    public boolean isThreeOpt() {
        return threeOpt;
    }

    public String getFilename() {
        return csvFileName;
    }

    public int getTotalMileage(){
        return totalMileage;
    }

    public void setPresenterReference(Presenter presenter) {
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
        csvPathOption.setRequired(false);
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
        useBGMap = true;//cmd.hasOption("bgmap");

        if (cmd.hasOption("csvpath")) {
            csvFileName = cmd.getOptionValue("csvpath");
        }

        if (cmd.hasOption("svgname")) {
            svgFileName = cmd.getOptionValue("svgname");
        }

        if (cmd.hasOption("xmlname")) {
            xmlFileName = cmd.getOptionValue("xmlname");
        }

        return 0;
    }

    public void setXmlFileName(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public String getKmlFileName() {
        return kmlFileName;
    }

    public void setKmlFileName(String kmlFileName) {
        this.kmlFileName = kmlFileName;
    }

    public String getHtmlFileName() {
        return htmlFileName;
    }

    public void setHtmlFileName(String htmlFileName) {
        this.htmlFileName = htmlFileName;
    }

    public boolean isGUIOn() {
        return useGUI;
    }
}
