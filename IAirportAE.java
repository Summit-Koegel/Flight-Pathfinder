// --== CS400 File Header Information ==--
// Name: Summit Koegel
// Email: skoegel@wisc.edu
// Team: DQ
// TA: Ilay Raz
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.List;

/**
 * interface for AirportAE
 * @author chenyuntzu
 *
 */
public interface IAirportAE extends GraphADT<IAirport>{
  
  /**
   * user give a source airport, then list all the vertex direct to the source vertex
   * @param source the data item contained in the source vertex for the edge
   * @return list of data item in vertices which direct to the source vertex
   */
  
  public List<IAirport> getDirectVertex(String airport);
  
  /**
   * give user the duration about this flight
   * @param distance the distance about this flight
   * @return the duration of flight
   */
  public double getDuration(double distance);
  
  /**
   * give the user the cost of this flight
   * @param hour the duration about this transport
   * @return the cost of flight
   */
  public double getCost(double hour);
  
  /**
   * display all the city can connect to which ciyt let user can easy to check
   */
  public void printMap();
  
  

}
