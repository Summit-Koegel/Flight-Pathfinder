import java.util.List;

/**
 * Backend class that loads the data set, search graph for shortest flight, 
 * finds airports in a given location and finds flights out of a given location 
 * Calls the AE's graphADT class to instantiate a graph and manipulate it
 * */
public interface IFlightRouteBackend<T> {
  
  /**
   * Loads data set from data wrangler
   * */
  public void loadFlights ( );
  
  /**
   * Given a start destination and end destination returns the shortest flight distance
   * The last 
   * @param start, start destination as string
   * @param end, end destination as string
   * @return list of airports that the flight must take
   * */
  public List<T> findFlight ( T start, T end );
  
  /**
   * Given a city, finds all airports in that city
   * @param city, name of city to search
   * @return list of airports found
   * */
  public List<IAirport> findAirportsInCity ( String city );
  
  /**
   * Given a state, finds all airports in that state
   * @param state, name of state to search
   * @return list of airports found
   * */
  public List<IAirport> findAirportsInState ( String state );
  
  /**
   * Given a city name, returns a List of all the airports that have connects to given airport
   * Connections must fly out of input city. 
   * @param city, string of city name
   * @return list of airports
   * */
  public List<T> findDirectFlightsOutOf ( T city );
  
  /**
   * Displays all the cities the user can fly out of and into. Makes it easier for the user to make
   * a flight
   * */
  public void showMap ( );  
}
