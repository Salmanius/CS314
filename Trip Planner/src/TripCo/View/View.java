package TripCo.View;

public class View {
    private boolean showMileage;
    private boolean showID;
    private boolean showName;
    private String filename;

    public View(String[] args) {
        showMileage = false;
        showID = false;
        showName = false;
        if (CommandParser(args) == 0) {
            //the parser didn't throw an error
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

    /* parses the arguments from the command line and stores them locally */
    private int CommandParser(String[] args) {
        int argsLength = args.length;

        if (argsLength == 1) {
            System.out.println("ERROR: The input file name must be included as an argument!");
            return -1;
        }

        char flag;
        if (argsLength > 2) {
            for(int i = 1; i < argsLength-1; i++) {
                String flags = args[i];
                for(int j = 1; j < flags.length(); j++) {
                    flag = flags.charAt(j);
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
                            System.out.println("ERROR: The flag " + flags + " is not a valid flag!");
                            break;
                    }
                }
            }
        }

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
