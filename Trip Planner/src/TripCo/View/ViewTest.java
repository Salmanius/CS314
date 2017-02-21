package TripCo.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ViewTest {
    @Test
    public void getMileageFlag() throws Exception {
        String[] testArgs = {"test.java", "-m", "input.csv"};
        View test = new View(testArgs);
        assertTrue(test.getMileageFlag());
    }

    @Test
    public void getIDFlag() throws Exception {
        String[] testArgs = {"test.java", "-i", "input.csv"};
        View test = new View(testArgs);
        assertTrue(test.getIDFlag());
    }

    @Test
    public void getNameFlag() throws Exception {
        String[] testArgs = {"test.java", "-n", "input.csv"};
        View test = new View(testArgs);
        assertTrue(test.getNameFlag());
    }

    @Test
    public void getFilename() throws Exception {
        String[] testArgs = {"test.java", "input.csv"};
        View test = new View(testArgs);
        assertEquals(test.getFilename(), "input.csv");
    }

    @Test
    public void addLocation() throws Exception {
        String[] testArgs = {"test.java", "-mi", "input.csv"};
        View test = new View(testArgs);
        test.addLocation("Fort Collins", "970", 105.0844, 40.5853, 20);
        
    }

    @Test
    public void printFiles() throws Exception {

    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void display() throws Exception {

    }

}