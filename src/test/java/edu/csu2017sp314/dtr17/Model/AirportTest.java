package test.java.edu.csu2017sp314.dtr17.Model;

import main.java.edu.csu2017sp314.dtr17.Model.Airport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Benas on 4/25/2017.
 */
public class AirportTest {
    Airport airy;

    @Before
    public void setUp() throws Exception {
        airy = new Airport();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getID() throws Exception {
        airy.setID("1234");
        assertEquals(airy.getID(), "1234");
    }

    @Test
    public void setID() throws Exception {
        airy.setID("1234");
        assertEquals(airy.getID(),"1234");
    }

    @Test
    public void getName() throws Exception {
        airy.setName("AiryTheAirport");
        assertEquals(airy.getName(), "AiryTheAirport");
    }

    @Test
    public void setName() throws Exception {
        airy.setName("AiryTheAirport");
        assertEquals(airy.getName(), "AiryTheAirport");
    }

    @Test
    public void getContinent() throws Exception {
        airy.setContinent("Eurasia");
        assertEquals(airy.getContinent(), "Eurasia");
    }

    @Test
    public void setContinent() throws Exception {
        airy.setContinent("Eurasia");
        assertEquals(airy.getContinent(), "Eurasia");
    }

    @Test
    public void getLongitude() throws Exception {
        airy.setLongitude("100");
        assertEquals(airy.getLongitude(), "100");
    }

    @Test
    public void setLongitude() throws Exception {
        airy.setLongitude("100");
        assertEquals(airy.getLongitude(), "100");
    }

    @Test
    public void getLatitude() throws Exception {
        airy.setLatitude("100");
        assertEquals(airy.getLatitude(), "100");
    }

    @Test
    public void setLatitude() throws Exception {
        airy.setLatitude("100");
        assertEquals(airy.getLatitude(), "100");
    }

    @Test
    public void getElevation() throws Exception {
        airy.setElevation("100");
        assertEquals(airy.getElevation(), "100");
    }

    @Test
    public void setElevation() throws Exception {
        airy.setElevation("100");
        assertEquals(airy.getElevation(), "100");
    }

    @Test
    public void getIso_country() throws Exception {
        airy.setIsoCountry("happy");
        assertEquals(airy.getIsoCountry(), "happy");
    }

    @Test
    public void setIso_country() throws Exception {
        airy.setIsoCountry("happy");
        assertEquals(airy.getIsoCountry(), "happy");
    }

    @Test
    public void getIso_region() throws Exception {
        airy.setIsoRegion("sad");
        assertEquals(airy.getIsoRegion(),"sad");
    }

    @Test
    public void setIso_region() throws Exception {
        airy.setIsoRegion("sad");
        assertEquals(airy.getIsoRegion(),"sad");
    }

    @Test
    public void getMunicipality() throws Exception {
        airy.setMunicipality("clouds");
        assertEquals(airy.getMunicipality(), "clouds");
    }

    @Test
    public void setMunicipality() throws Exception {
        airy.setMunicipality("clouds");
        assertEquals(airy.getMunicipality(), "clouds");
    }

    @Test
    public void getScheduled_service() throws Exception {
        airy.setScheduledService("truck");
        assertEquals(airy.getScheduledService(), "truck");
    }

    @Test
    public void setScheduled_service() throws Exception {
        airy.setScheduledService("truck");
        assertEquals(airy.getScheduledService(), "truck");
    }

    @Test
    public void getGps_code() throws Exception {
        airy.setGps_code("gpsstuffs");
        assertEquals(airy.getGpsCode(), "gpsstuffs");
    }

    @Test
    public void setGps_code() throws Exception {
        airy.setGps_code("gpsstuffs");
        assertEquals(airy.getGpsCode(), "gpsstuffs");
    }

    @Test
    public void getIata_code() throws Exception {
        airy.setIataCode("blargh");
        assertEquals(airy.getIataCode(), "blargh");
    }

    @Test
    public void setIata_code() throws Exception {
        airy.setIataCode("blargh");
        assertEquals(airy.getIataCode(), "blargh");
    }

    @Test
    public void getLocal_code() throws Exception {
        airy.setLocalCode("honk");
        assertEquals(airy.getLocalCode(), "honk");
    }

    @Test
    public void setLocal_code() throws Exception {
        airy.setLocalCode("honk");
        assertEquals(airy.getLocalCode(), "honk");
    }

    @Test
    public void getHome_link() throws Exception {
        airy.setHomeLink("scales");
        assertEquals(airy.getHomeLink(), "scales");
    }

    @Test
    public void setHome_link() throws Exception {
        airy.setHomeLink("scales");
        assertEquals(airy.getHomeLink(), "scales");
    }

    @Test
    public void getWikipedia_link() throws Exception {
        airy.setWikipediaLink("wiki");
        assertEquals(airy.getWikipediaLink(), "wiki");
    }

    @Test
    public void setWikipedia_link() throws Exception {
        airy.setWikipediaLink("wiki");
        assertEquals(airy.getWikipediaLink(), "wiki");
    }

    @Test
    public void getKeywords() throws Exception {
        airy.setKeywords("key");
        assertEquals(airy.getKeywords(), "key");
    }

    @Test
    public void setKeywords() throws Exception {
        airy.setKeywords("key");
        assertEquals(airy.getKeywords(), "key");
    }

}