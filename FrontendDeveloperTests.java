import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

/**
 * Tests the FlightRouteFrontend class to make sure it outputs what it should
 */
public class FrontendDeveloperTests {

    FlightRouteFrontend f;
    TextUITester t;

    /**
     * Initialize frontend and TextUITester
     */
    @BeforeEach
    public void init() {
        f = new FlightRouteFrontend(new FlightRouteBackendFD<IAirport>());
        t = new TextUITester("");
    }

    /**
     * Test the main command window output
     */
    @Test
    public void testCommandMenu() {
        f.printCommandMenu();
        String r = t.checkOutput();
        assert(r.contains("PLEASE SELECT AN OPTION:"));
        assert(r.contains("[F]IND PATH BETWEEN TWO AIRPORTS"));
        assert(r.contains("[L]IST FLIGHTS FROM AN AIRPORT"));
        assert(r.contains("[A]DD FLIGHT CONNECTION"));
        assert(r.contains("[R]EMOVE FLIGHT CONNECTION"));
        assert(r.contains("[Q]UIT"));
    }

    /**
     * Test the shortest path search output
     */
    @Test
    public void testShortestPath() {
        // test input with valid flight path
        t = new TextUITester("ORD"+System.getProperty("line.separator")+
                "MKE"+System.getProperty("line.separator"));
        f.shortestPathSearch();
        String r = t.checkOutput();
        assert(r.contains("FLIGHT FOUND:"));
        assert(r.contains("MKE--->LAX--->ORD"));
        // test input with no flight path
        t = new TextUITester("JFK"+System.getProperty("line.separator")+
                "MKE"+System.getProperty("line.separator"));
        f.shortestPathSearch();
        r = t.checkOutput();
        assert(r.contains("NO FLIGHT FOUND"));
    }

    /**
     * Test the connection search output
     */
    @Test
    public void testConnection() {
        // test input with connections
        t = new TextUITester("milwaukee"+System.getProperty("line.separator"));
        f.connectionSearch();
        String r = t.checkOutput();
        assert(r.contains("FLIGHTS AVAILABLE:"));
        assert(r.contains("JFK, LAX, ORD, BOS"));
        // test input with no connections
        t = new TextUITester("chicago"+System.getProperty("line.separator"));
        f.connectionSearch();
        r = t.checkOutput();
        assert(r.contains("NO FLIGHTS FOUND"));
    }

    /**
     * Test the output for making a connection
     */
    @Test
    public void testAddConnection() {
        // test making a connection
        t = new TextUITester("ORD"+System.getProperty("line.separator")+
                "MKE"+System.getProperty("line.separator"));
        f.addConnection();
        String r = t.checkOutput();
        assert(r.contains("NEW CONNECTION MADE FROM MKE TO ORD"));
    }

    /**
     * Test the output for removing a connection
     */
    @Test
    public void testRemoveConnection() {
        // test removing a connection
        t = new TextUITester("ORD"+System.getProperty("line.separator")+
                "MKE"+System.getProperty("line.separator"));
        f.removeConnection();
        String r = t.checkOutput();
        assert(r.contains("CONNECTION REMOVED FROM MKE TO ORD"));
    }

    /**
     * Tests the output when searching for a flight using correct backend
     */
    @Test
    public void testFlightSearchIntegrated() {
	f = new FlightRouteFrontend(new FlightRouteBackend<IAirport>());
        // test input with valid flight path
        t = new TextUITester("ORD"+System.getProperty("line.separator")+
                "MKE"+System.getProperty("line.separator"));
        f.shortestPathSearch();
        String r = t.checkOutput();
        assert(r.contains("FLIGHT FOUND:"));
        assert(r.contains("MKE--->ORD"));
        // test input with no flight path
        t = new TextUITester("ZZZ"+System.getProperty("line.separator")+
                "XXX"+System.getProperty("line.separator"));
        f.shortestPathSearch();
        r = t.checkOutput();
        assert(r.contains("NO FLIGHT FOUND"));
     }

    /**
     * Test the output for making a connection using correct backend
     */
    @Test
    public void testAddConnectionIntegrated() {
	f = new FlightRouteFrontend(new FlightRouteBackend<IAirport>());
        // test making a connection (valid)
        t = new TextUITester("PEK"+System.getProperty("line.separator")+
                "MKE"+System.getProperty("line.separator"));
        f.addConnection();
        String r = t.checkOutput();
        assert(r.contains("NEW CONNECTION MADE FROM MKE TO ORD"));
        // test making a connection (invalid)
        t = new TextUITester("ZZZ"+System.getProperty("line.separator")+
                "XXX"+System.getProperty("line.separator"));
        f.addConnection();
        r = t.checkOutput();
        assert(r.contains("CONNECTION REMOVAL FAILED"));
    }
    
    /**
     * Test Data Wrangler's Airport implementation
     */
    @Test
    public void testAirport() {
	    Airport test = new Airport("MKE", "Milwaukee", "WI");
	    assert(test.getAirportCode().equals("MKE"));
	    assert(test.getCity().equals("Milwaukee"));
	    assert(test.getState().equals("NAN"));

	    test = new Airport();
	    String d = "NAN";
	    assert(test.getAirportCode().equals(d));
	    assert(test.getCity().equals(d));
	    assert(test.getState().equals(d));
    }

    /**
     * Test Data Wrangler's Loader implementation
     */
    @Test
    public void testLoader() {
	    try {
	    	FlightLoader fl = new FlightLoader();
	    } catch (Exception e) {assert(false);}

	    assert(fl.loadConnections().size() > 0);
	    assert(fl.loadDistances().size() > 0);
	    assert(fl.allAirportsList().size > 0);
	    assert(fl.allAirportsHash().getOrDefault("MKE", null) != null);
	    assert(fl.allAirportsList().toString().contains("MKE"));
    }
}
