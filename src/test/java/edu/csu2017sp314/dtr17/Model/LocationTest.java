package TripCo.Model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mjdun on 2/21/2017.
 */
public class LocationTest {
    @Test
    public void getDblLatitude() throws Exception {
        Location A = new Location("0","testA", "40.578629","-105.085226");
        Location B = new Location("1","testB","37°28'20\" N", "106°47'35\" W");
        Location C = new Location("2", "testC", "38.39°N", "102.76°W");

        assertEquals(40.578, A.getDblLatitude(), 0.005);
        assertEquals(37.47, B.getDblLatitude(), 0.005);
        assertEquals(38.39, C.getDblLatitude(), 0.005);
    }

    @Test
    public void getDblLongitude() throws Exception {
        Location A = new Location("0","testA", "40.578629","-105.085226");
        Location B = new Location("1","testB","37°28'20\" N", "106°47'35\" W");
        Location C = new Location("2", "testC", "38.39°N", "102.76°W");

        assertEquals(-105.085, A.getDblLongitude(), 0.005);
        assertEquals(-106.79, B.getDblLongitude(), 0.005);
        assertEquals(-102.76, C.getDblLongitude(), 0.005);
    }

    @Test
    public void getName() throws Exception {
        Location A = new Location("0","testA", "40.578629","-105.085226");

        assertEquals("testA", A.getName());
    }

    @Test
    public void getStringLatitude() throws Exception {
        Location A = new Location("0","testA", "40.578629","-105.085226");

        assertEquals("40.578629", A.getStringLatitude());
    }

    @Test
    public void getStringLongitude() throws Exception {
        Location A = new Location("0","testA", "40.578629","-105.085226");

        assertEquals("-105.085226", A.getStringLongitude());

    }

}