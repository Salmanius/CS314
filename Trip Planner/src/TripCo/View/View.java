package TripCo.View;

/**
 * Created by mjdun on 2/16/2017.
 */
public class View {
    public View() {
    }
    public void display(String text) {
        System.out.println(text);
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
                "  <line id=\"north\" y2=\""+ y1 +"\" x2=\""+ x2 +"\" y1=\""+ y1 +"\" x1=\""+ x1 +"\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                "  <line id=\"east\" y2=\""+ y2 +"\" x2=\""+ x2 +"\" y1=\""+ y1 +"\" x1=\""+ x2 +"\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                "  <line id=\"south\" y2=\""+ y2 +"\" x2=\""+ x1 +"\" y1=\""+ y2 +"\" x1=\""+ x2 +"\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                "  <line id=\"west\" y2=\""+ y1 +"\" x2=\""+ x1 +"\" y1=\""+ y2 +"\" x1=\""+ x1 +"\" stroke-width=\"5\" stroke=\"#666666\"/>\n" +
                " </g>";

        return borderString;
    }
}
