
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
}
                         
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
  public ArrayList<IAirport> loadConnections();//returns all airport connections
  /**
   *
   * @return a list of distances corresponding to the pairs of airports above
   */
  public ArrayList<Integer> loadDistances();
}


