package test.java.edu.csu2017sp314.dtr17.View;

import main.java.edu.csu2017sp314.dtr17.View.TripFileCreator;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
/**
 * Created by Chris on 4/11/2017.
 */
public class TripFileCreatorTest {

    @Test
    public void addLocation(){

    }

    @Test
    public void longToPix(){
        TripFileCreator t = new TripFileCreator();
        assertEquals(1078,t.longToPix(35.5), .001);
        assertEquals(1595,t.longToPix(139), .001);
        assertEquals(1395,t.longToPix(99), .001);
    }

    @Test
    public void latToPix(){
        TripFileCreator t = new TripFileCreator();
        assertEquals(272,t.latToPix(35.5), .001);
        assertEquals(-245,t.latToPix(139), .001);
        assertEquals(-45,t.latToPix(99), .001);
    }

    @Test
    public void writeLegsToSVG(){

    }

    @Test
    public void writeTitlesToSVG(){

    }

    @Test
    public void wriiteMileagesToSVG(){

    }

    @Test
    public void writeHeader(){

    }

    @Test
    public void writeBackground(){

    }

    @Test
    public void printSVGFile(){

    }

    @Test
    public void printXMLFile(){

    }

    @Test
    public void wrapAround(){
        TripFileCreator t = new TripFileCreator();
        assertEquals(false,t.wrapAround(15,10));
        assertEquals(true,t.wrapAround(0,1500));
    }

    @Test
    public void printRightWrap(){

    }

    @Test
    public void printLeftWrap(){

    }

    @Test
    public void getItineraryStrings(){
        TripFileCreator t = new TripFileCreator();
        assertEquals(null,t.getItineraryStrings());
    }

}
