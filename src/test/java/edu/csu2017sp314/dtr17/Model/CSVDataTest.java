package test.java.edu.csu2017sp314.dtr17.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import main.java.edu.csu2017sp314.dtr17.Model.*;

/**
 * Created by mjdun on 2/20/2017.
 */
public class CSVDataTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void addColumn() throws Exception {
        CSVData csvData = new CSVData();

        csvData.addColumn("names");

        assertEquals(1, csvData.getWidth());
        assertEquals(0, csvData.getHeight());
    }

    @Test
    public void addDataToColumn() throws Exception {
        CSVData csvData = new CSVData();
        csvData.addColumn("names");
        csvData.addColumn("coolness level");

        csvData.addDataToColumn("names", "matt");
        csvData.addDataToColumn("coolness level", "9000+");

        assertEquals("matt", csvData.getValue("names", 0 ));
        assertEquals("9000+", csvData.getValue("coolness level", 0 ));
    }

    @Test
    public void getValue() throws Exception {
        CSVData csvData = new CSVData();
        csvData.addColumn("names");
        csvData.addColumn("coolness level");

        csvData.addDataToColumn("names", "matt");
        csvData.addDataToColumn("coolness level", "9000+");

        assertEquals("matt", csvData.getValue("names", 0 ));
        assertEquals("9000+", csvData.getValue("coolness level", 0 ));
    }

    @Test
    public void getHeight() throws Exception {
        CSVData csvData = new CSVData();
        csvData.addColumn("names");
        csvData.addColumn("coolness level");

        csvData.addDataToColumn("names", "matt");
        csvData.addDataToColumn("coolness level", "9000+");

        assertEquals(1, csvData.getHeight());
    }

    @Test
    public void getWidth() throws Exception {
        CSVData csvData = new CSVData();
        csvData.addColumn("names");
        csvData.addColumn("coolness level");

        csvData.addDataToColumn("names", "matt");
        csvData.addDataToColumn("coolness level", "9000+");

        assertEquals(2, csvData.getWidth());

    }

}