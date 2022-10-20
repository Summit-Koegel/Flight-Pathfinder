import java.util.ArrayList;

public class FlightLoaderPL implements IFlightLoader{
  ArrayList<IAirport> airports = new ArrayList<IAirport>();
  ArrayList<Integer> connections = new ArrayList<Integer>();

  @Override public ArrayList<IAirport> loadConnections() {
    airports.add(new AirportPL("ORD", "Illinois", "Chicago"));
    return airports;
  }

  @Override public ArrayList<Integer> loadDistances() {
    connections.add(5);
    return connections;
  }
}

