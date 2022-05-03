// --== CS400 Project 4 File Header ==--
// Name: Aaryush Gupta
// CSL Username: aaryush
// Email: agupta276@wisc.edu
// Lecture #: 004
// Notes to Grader: <Notes>


import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Backend class that loads the data set, search graph for shortest flight,
 * finds airports in a given location and finds flights out of a given location
 * Calls the AE's graphADT class to instantiate a graph and manipulate it
 */
public class FlightRouteBackend<T> implements IFlightRouteBackend {
  public static void main(String[] args) throws Exception {
    FlightRouteBackend<String> backend = new FlightRouteBackend<String>();
    backend.loadFlights();
    System.out.println(backend.findDirectFlightsOutOf("Chicago"));
  }
  IFlightLoader loader = new FlightLoaderPL();
  IAirportAE ae = new AirportAEPL();
  ArrayList<IAirport> airports = new ArrayList<IAirport>();
  ArrayList<Integer> distances = new ArrayList<Integer>();

  /**
   * Loads data set from data wrangler
   **/
  @Override public void loadFlights() {
	//TODO: actual flight loder works a little bit differently
	// all airports can be found in allAirportsList
	// not in the individual airports
	// load connections is actually an ArrayList<IAirport[]> {origin, destinations}
	// the connections are at the same indicies as the distances
	// there are other options to use as well in the interface
    airports = loader.loadConnections();
    distances = loader.loadDistances();
  }

  /**
   * Given a start destination and end destination returns the shortest flight distance
   *
   * @param start, start destination as string
   * @param end,   end destination as string
   * @return list of airports that the flight must take
   */
  @Override public List<String> findFlight(Object start, Object end) {
    ArrayList<String> result = new ArrayList<String>();
    for (String[] list: ae.getLastThreeShortestPath((String) start, (String) end)){
      result.add(list[0]);
    }
    return result;
  }

  /**
   * Given a city, finds all airports in that city
   *
   * @param city, name of city to search
   * @return list of airports found
   */
  @Override public List<IAirport> findAirportsInCity(String city) {
    List<IAirport> result = new ArrayList<IAirport>();
    for(IAirport airport: airports){
      if (airport.getCity().equals(city))
        result.add(airport);
    }
    return result;
  }

  /**
   * Given a state, finds all airports in that state
   *
   * @param state, name of state to search
   * @return list of airports found
   */
  @Override public List<IAirport> findAirportsInState(String state) {
    List<IAirport> result = new ArrayList<IAirport>();
    for(IAirport airport: airports){
      if (airport.getState().equals(state))
        result.add(airport);
    }
    return result;
  }

  /**
   * Given a city name, returns a List of all the airports that have connects to given airport
   * Connections must fly out of input city.
   *
   * @param city, string of city name
   * @return list of airports
   */
  @Override public List<String> findDirectFlightsOutOf(Object city) {
    List<String> result = new ArrayList<String>();
    List<IAirport> temp = this.findAirportsInCity((String)city);
    for (IAirport a:temp)
      result.addAll(ae.getDirectVertex(a.getAirportCode()));
    Set<String> set = new LinkedHashSet<String>(result);
    result.clear();
    result.addAll(set);
    return result;
  }

  /**
   * Displays all the cities the user can fly out of and into. Makes it easier for the user to make
   * a flight
   */
  @Override public void showMap() {
    for (IAirport airport: airports){
      System.out.println("Airport: " + airport.getAirportCode());
      for (String direct: this.findDirectFlightsOutOf(airport.getCity())){
        System.out.print(direct + "\n");
      }
    }
  }
}

