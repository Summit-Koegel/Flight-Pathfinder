import java.util.ArrayList;
import java.util.Hashtable;

/**
import java.util.ArrayList;

/**
 * interface for loader for flight
 *
 *
 * @author charlie jungwirth
 * @author Austin Wright
 *
 */
public interface IFlightLoader {

        /**
         *
         * @return a list of Airports in data file
         */
        public ArrayList<IAirport[]> loadConnections();//returns all airport connections
        
        /**
         * 
         * @return a list of distances corresponding to the pairs of airports above
         */
         public ArrayList<Integer> loadDistances();
         
         /**
          * 
          * @return list of all airports loaded in
          */
         public ArrayList<IAirport> allAirportsList();
         
         /**
          * 
          * @return hashtable of all airports based on their 3 char key
          */
         public Hashtable<String, IAirport> allAirportsHash();
         
         /**
          * 
          * @return all flights in flight object instead of seperate connections+dist
          */
         public ArrayList<Flight> loadFlights();
}
                         

