package test.java.edu.csu2017sp314.dtr17.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import main.java.edu.csu2017sp314.dtr17.Model.*;

/**
 * Created by mjdun on 2/20/2017.
 */
public class CSVParserTest {
    @Before
    public void setUp() throws Exception {

    }
    /*
    @Test
    public void parse() throws Exception {
        CSVParser test = new CSVParser("src\\TestData\\ColoradoSkiResorts.csv");

        test.parse();
        CSVData csvData = test.getCSVData();

        assertEquals("Telluride", csvData.getValue("name", 1));
        assertEquals(" 38°53'56\" N", csvData.getValue("Latitude", 5));
        assertEquals("105°45'38\" W", csvData.getValue("LONGITUDE", 15));
    }

    @Test
    public void getCSVData() throws Exception {
        CSVParser test = new CSVParser("src\\TestData\\ColoradoSkiResorts.csv");

        test.parse();
        CSVData csvData = test.getCSVData();

        assertEquals("Telluride", csvData.getValue("name", 1));
        assertEquals(" 38°53'56\" N", csvData.getValue("Latitude", 5));
        assertEquals("105°45'38\" W", csvData.getValue("LONGITUDE", 15));
    }
    */
}
