// --== CS400 File Header Information ==--
// Name: Charles Jungwirth
// Email: crjungwirth@wisc.edu
// Team: DQ
// TA: Ilay Raz
// Lecturer: Florian Heimerl
// Notes to Grader: Allows multiple options for loading flights, flight objects optional
//	Data file generated from csvTojson.java
//	initial csv file is very big so I didn't put it on google machine
// also are some duplicate paths on csv

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DataWranglerTests {
	
	
	/**
	 * test to see if first 20 distances match up with file
	 */
	@Test
	public void testFirstDistances() {
		try {
			FlightLoader loader = new FlightLoader();
			int[] dists = new int[] {116,462,526,228,1387,813,1299,0,1253,230,1033
					,230,230,1788,1212,879,1075,1350,502,1245};//distances of first 20
			for(int i = 0; i<dists.length; i++) {
				assertEquals(dists[i],loader.loadDistances().get(i));
			}
		} catch (Exception e) {
			
			fail("unexpected Exception");
		}
		
		
	}
	
	/**
	 * tests to see if first 8 paths in file are right
	 */
	@Test
	public void testFirstPaths() {
		try {
			FlightLoader loader = new FlightLoader();
			String[] origs = new String[] {"PDX", "SFO", "SLC", "SEA", "HOU", "ELP", "DBQ", "EKO"};
			String[] dests = new String[] {"RDM", "RDM", "RDM", "RDM", "EKO", "EKO", "EKO", "EKO"};
			for(int i = 0; i< origs.length; i++) {
				assertEquals(origs[i], loader.loadConnections().get(i)[0].getAirportCode());
				assertEquals(dests[i], loader.loadConnections().get(i)[1].getAirportCode());
			}
		}catch(Exception e) {
			fail("unexpected exception");
		}
	}
	
	
	/**
	 * sees if hashtable has all of airport's keys
	 */
	@Test
	public void testAirportHash() {
		try {
			FlightLoader loader = new FlightLoader();
			for(int i = 0; i< loader.loadConnections().size(); i++) {
			
				assertEquals(true, loader.allAirportsHash().containsKey(loader.loadConnections().get(i)[0].getAirportCode()));
				assertEquals(true, loader.allAirportsHash().containsKey(loader.loadConnections().get(i)[1].getAirportCode()));
			}
		} catch (Exception e) {
			fail("unexpected Exception");
		}
	}
	
	/**
	 * tests to see if last flight is expected
	 */
	@Test
	public void testLastFlight() {
		try {
			FlightLoader loader = new FlightLoader();
			assertEquals(119, loader.loadFlights().get(loader.loadFlights().size()-1).distance);
			assertEquals("STL", loader.loadFlights().get(loader.loadFlights().size()-1).origin.getAirportCode());
		}catch (Exception e) {
			fail("unexpected exception");
		}
	}
	
	/**
	 * tests to see if city/state names are right in first elements
	 */
	@Test
	public void testFirstCityState() {
		try {
			FlightLoader loader = new FlightLoader();
			String[] city = new String[] {"Portland", "San Francisco", "Salt Lake City", "Seattle", "Houston", "El Paso", "Dubuque", "Elko"};
			String[] state= new String[] {"OR", "CA", "UT", "WA", "TX", "TX", "IA", "NV"};
			
			for(int i = 0; i< city.length; i++) {
				assertEquals(city[i], loader.loadConnections().get(i)[0].getCity());
				assertEquals(state[i], loader.loadConnections().get(i)[0].getState());
			}
		}catch(Exception e) {
			fail("unexpected exception");
		}
	}
	
		

}
