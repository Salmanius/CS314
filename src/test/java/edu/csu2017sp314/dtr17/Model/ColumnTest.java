package test.java.edu.csu2017sp314.dtr17.Model;

import org.junit.Before;
import org.junit.Test;

import main.java.edu.csu2017sp314.dtr17.Model.*;

import static org.junit.Assert.*;

/**
 * Created by mjdun on 2/20/2017.
 */
public class ColumnTest {
    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void add() throws Exception {
        Column test = new Column("name");
        test.add("matt");

        assertEquals("matt", test.getDataAt(0));
    }

    @Test
    public void getDataAt() throws Exception {
        Column test = new Column("name");
        test.add("matt");

        assertEquals("matt", test.getDataAt(0));
    }

    @Test
    public void getColumnName() throws Exception {
        Column test = new Column("id");

        assertEquals("id", test.getColumnName());
    }

    @Test
    public void getHeight() throws Exception {
        Column test = new Column("name");
        test.add("matt");
        test.add("chris");
        test.add("ben");

        assertEquals(3, test.getHeight());
    }

}