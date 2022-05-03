import java.util.ArrayList;
import java.util.List;

/**
 * Hard coded implementation of FlightRouteBackend.java
 */
public class FlightRouteBackendFD<T> implements IFlightRouteBackend<IAirport> {
    /**
     * Not needed for frontend
     */
    @Override
    public void loadFlights() {
        // not needed for frontend
    }

    /**
     * Returns a hardcoded flight path
     */
    @Override
    public List<IAirport> findFlight(IAirport start, IAirport end) {
        ArrayList<IAirport> r = new ArrayList<IAirport>();
        if(start.getAirportCode().equals("MKE") && end.getAirportCode().equals("ORD")) {
            r.add(new AirportFD("MKE","",""));
            r.add(new AirportFD("LAX","",""));
            r.add(new AirportFD("ORD","",""));
            return r;
        } else {
            return r;
        }
    }

    /**
     * Not needed for frontend
     */
    @Override
    public List<IAirport> findAirportsInCity(String city) {
        return null;
    } // not needed for frontend

    /**
     * Not needed for frontend
     */
    @Override
    public List<IAirport> findAirportsInState(String state) {
        return null;
    } // not needed for frontend

    /**
     * Returns a hardcoded list of flights out of milwaukee
     */
    @Override
    public List<IAirport> findDirectFlightsOutOf(IAirport city) {
        ArrayList<IAirport> r = new ArrayList<IAirport>();
        if(city.getCity().toLowerCase().equals("milwaukee")) {
            r.add(new AirportFD("JFK","",""));
            r.add(new AirportFD("LAX","",""));
            r.add(new AirportFD("ORD","",""));
            r.add(new AirportFD("BOS","",""));
            return r;
        } else {
            return r;
        }
    }

    /**
     * Not needed for frontend
     */
    @Override
    public void showMap() {

    } // not needed for frontend

    /**
     * Not needed for frontend
     */
    //@Override
    //public String[] getFlightInformation(IAirport start, IAirport end) {
     //   return new String[0];
   // } // not needed for frontend

    /**
     * Method for adding connection between two airports (hardcoded)
     */
    public boolean makeConnection(IAirport s, IAirport d) {
        return true;
    } // not in interface, but needs to be added

    /**
     * Method for removing connection between two airports (hardcoded)
     */
    public boolean removeConnection(IAirport srcA, IAirport destA) {
        return true;
    } // not in interface, but needs to be added
    

    /**
     * Hardcoded method to return the 3 shortest paths between two airports
     */
    public List<List<IAirport>> threeShortestPaths(IAirport start, IAirport end) {
        ArrayList<List<IAirport>> r = new ArrayList<List<IAirport>>();
        if(start.getAirportCode().equals("MKE") && end.getAirportCode().equals("ORD")) {
	    for(int i = 0; i < 3; i++) {
		    ArrayList<IAirport> t = new ArrayList<IAirport>();
   	            t.add(new AirportFD("MKE","",""));
        	    t.add(new AirportFD("LAX","",""));
           	    t.add(new AirportFD("ORD","",""));
		    r.add(t);
 	    }
           return r;
        } else {
            return r;
        }
    } // not in interface, needs to be added

}
