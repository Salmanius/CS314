package test.java.edu.csu2017sp314.dtr17.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import main.java.edu.csu2017sp314.dtr17.View.*;

public class ViewTest {
    @Test
    public void getMileageFlag() throws Exception {
        String[] testArgs = {"test.java", "-p input.csv", "-m", "input.csv"};
        View test = new View(testArgs);
        assertTrue(test.getMileageFlag());
    }

    @Test
    public void getIDFlag() throws Exception {
        String[] testArgs = {"test.java", "-p input.csv", "-i", "input.csv"};
        View test = new View(testArgs);
        assertTrue(test.getIDFlag());
    }

    @Test
    public void getNameFlag() throws Exception {
        String[] testArgs = {"test.java", "-p input.csv", "-n", "input.csv"};
        View test = new View(testArgs);
        assertTrue(test.getNameFlag());
    }

    @Test
    public void getFilename() throws Exception {
        String[] testArgs = {"test.java", "-p input.csv", "input.csv"};
        View test = new View(testArgs);
        assertEquals(test.getFilename(), " input.csv");
    }

    @Test
    public void addLocation() throws Exception {
        String[] testArgs = {"test.java", "-mi", "-p input.csv", "input.csv"};
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

}