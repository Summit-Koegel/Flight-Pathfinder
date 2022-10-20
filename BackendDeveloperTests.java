import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BackendDeveloperTests {
  FlightRouteBackend<String> backend;
  @BeforeEach public void createInstance() {
    backend = new FlightRouteBackend<String>();
    backend.loadFlights();
  }

  /**
   * Tests if PL loader is correctly being loaded into backend
   */
  @Test
  public void testLoad(){
    assertEquals(backend.airports.size(), 1);
  }

  /**
   * Tests if the method getting airports in cities works as intended
   */
  @Test
  public void testAirportsInCity(){
    assertEquals(backend.findAirportsInCity("Chicago").get(0).getAirportCode(), "ORD");
  }

  /**
   * Tests if the method getting airports in state works as intended
   */
  @Test
  public void testAirportsInState(){
    assertEquals(backend.findAirportsInState("Illinois").get(0).getAirportCode(), "ORD");
  }

  /**
   * Tests if find flights returns the proper placeholder response to the method,
   * returning the correlated airport codes
   */
  @Test
  public void testFindFlight(){
    assertEquals(backend.findFlight("ORD", "LAX").toString(), "[ORD, ORD, BOM]");
  }

  /**
   * Tests if backend finds all flights leaving a given city
   */
  @Test
  public void testDirectFlights(){
    assertEquals(backend.findDirectFlightsOutOf("Chicago").toString(), "[LAX]");
  }
}

