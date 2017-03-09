package TripCo.Model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LegTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void setStartLocation() throws Exception {
        Leg testLeg = new Leg(0,100);
        Location testLocation = new Location("testID","test", "100", "-100");
        testLeg.setStartLocation(testLocation);

        assertEquals("test", testLeg.getStartName());
    }

    @Test
    public void setEndLocation() throws Exception {
        Leg testLeg = new Leg(0,100);
        Location testLocation = new Location("testID","test", "100", "-100");
        testLeg.setEndLocation(testLocation);

        assertEquals("test", testLeg.getEndName());
    }

    @Test
    public void setSequence() throws Exception {
        Leg testLeg = new Leg(0,100);
        testLeg.setSequence(1);
        assertEquals(1, testLeg.getSequence());
    }

    @Test
    public void getSequence() throws Exception {
        Leg testLeg = new Leg(0,100);

        assertEquals(0, testLeg.getSequence());
    }

    @Test
    public void getStartName() throws Exception {
        Leg testLeg = new Leg(0,100);
        Location testLocation = new Location("testID","test", "100", "-100");
        testLeg.setStartLocation(testLocation);

        assertEquals("test", testLeg.getStartName());
    }

    @Test
    public void getEndName() throws Exception {
        Leg testLeg = new Leg(0,100);
        Location testLocation = new Location("testID","test", "100", "-100");
        testLeg.setEndLocation(testLocation);

        assertEquals("test", testLeg.getEndName());
    }

    @Test
    public void getMileage() throws Exception {
        Leg testLeg = new Leg(0,100);

        assertEquals(100, testLeg.getMileage());
    }

}
