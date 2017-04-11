package test.java.edu.csu2017sp314.dtr17.Model;


import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;
import main.java.edu.csu2017sp314.dtr17.Model.*;

public class TripTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void setNameFlag() throws Exception {
        Trip testTrip = new Trip(false, false, false, 0);
        testTrip.setNameFlag(true);
        assertEquals(true, testTrip.getNameFlag());
    }

    @Test
    public void setIdFlag() throws Exception {
        Trip testTrip = new Trip(false, false, false, 0);
        testTrip.setIdFlag(true);
        assertEquals(true, testTrip.getIdFlag());
    }

    @Test
    public void setMileageFlag() throws Exception {
        Trip testTrip = new Trip(false, false, false, 0);
        testTrip.setMileageFlag(true);
        assertEquals(true, testTrip.getMileageFlag());
    }

    public void getNameFlag() throws Exception {
        Trip testTrip = new Trip(false, false, false, 0);
        testTrip.setNameFlag(true);
        assertEquals(true, testTrip.getNameFlag());
    }

    @Test
    public void getIdFlag() throws Exception {
        Trip testTrip = new Trip(false, false, false, 0);
        testTrip.setIdFlag(true);
        assertEquals(true, testTrip.getIdFlag());
    }

    @Test
    public void getMileageFlag() throws Exception {
        Trip testTrip = new Trip(false, false, false, 0);
        testTrip.setMileageFlag(true);
        assertEquals(true, testTrip.getMileageFlag());
    }
}
