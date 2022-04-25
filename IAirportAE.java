import java.util.List;

/**
 * interface for AirportAE
 * @author chenyuntzu
 *
 */
public interface IAirportAE extends GraphADT<String>{
  
  /**
   * user give a source airport, then list all the vertex direct to the source vertex
   * @param source the data item contained in the source vertex for the edge
   * @return list of data item in vertices which direct to the source vertex
   */
  public List<String> getDirectVertex(String airport);
  
  /**
   * user give a source airport and destination airport, then list last three shortest path 
   * @param source the airport you start
   * @param destination the airport you want to arrive
   * @return array of Linkedlist for last three shortest path from source to destination
   */
  public List<String>[] getLastThreeShortestPath(String source, String destination);
  
  /**
   * give user the duration about this flight
   * @param distance the distance about this flight
   * @return the duration of flight
   */
  public double getduration(double distance);
  
  /**
   * give the user the cost of this flight
   * @param hour the duration about this transport
   * @return the cost of flight
   */
  public double getcost(double hour);
  
  /**
   * display all the city can connect to which ciyt let user can easy to check
   */
  public void printMap();
  
  

}
