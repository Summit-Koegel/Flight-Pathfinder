// --== CS400 File Header Information ==--
// Name: Summit Koegel
// Email: skoegel@wisc.edu
// Team: DQ
// TA: Ilay Raz
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;


public class AlgorithmEngineerTests {

  private AirportAE graph;

  /**
   * Instantiate graph from last week's shortest path activity.
   */
  @BeforeEach
  public void createGraph() {
    graph = new AirportAE();

    //Create airports
    AirportPH port1 = new AirportPH("MAD","WI","Madison");
    AirportPH port2 = new AirportPH("MAF","WI","Belford");
    AirportPH port3 = new AirportPH("MIA","WAS","Miami");
    AirportPH port4 = new AirportPH("CHI","QQ","Chicago");
    AirportPH port5 = new AirportPH("MMP","WI","MemU");
    AirportPH port6 = new AirportPH("BOL","CO","Boulder");

    //Instantiate Vertices
    graph.insertVertex(port1);
    graph.insertVertex(port2);
    graph.insertVertex(port3);
    graph.insertVertex(port4);
    graph.insertVertex(port5);
    graph.insertVertex(port6);
    
    //Add edges and weights
    graph.insertEdge(port1, port2, 600);
    graph.insertEdge(port1, port3, 200);
    graph.insertEdge(port1, port4, 500);
    graph.insertEdge(port2, port5, 100);
    graph.insertEdge(port2, port3, 200);
    graph.insertEdge(port3, port2, 300);
    graph.insertEdge(port3, port6, 100);
    graph.insertEdge(port4, port5, 300);
    graph.insertEdge(port5, port1, 400);
    graph.insertEdge(port6, port1, 100);
    graph.insertEdge(port6, port4, 100);

  }

  /**
   * Tests that AirportAE can add airports and connect them with an edge that has a
   * weight
   */
  @Test
  public void testAirportAE() {
    AirportPH port1 = new AirportPH("MAD","WI","Madison");
    AirportPH port2 = new AirportPH("MAF","WI","Belford");
    AirportPH port3 = new AirportPH("MIA","WAS","Miami");
    AirportPH port4 = new AirportPH("CHI","QQ","Chicago");
    AirportPH port5 = new AirportPH("MMP","WI","MemU");
    AirportPH port6 = new AirportPH("BOL","CO","Boulder");

    //Checking if vertices and edges are in the correct place
    AirportPH port = new AirportPH("C","WI","Madison");
    assertEquals(6,graph.getVertexCount());
    assertEquals(11,graph.getEdgeCount());
    assertTrue(graph.containsVertex(port1));
    assertFalse(graph.containsVertex(port));
    assertEquals(600,graph.getWeight(port1, port2));
    assertEquals(400,graph.getWeight(port5, port1));
    assertTrue(graph.containsVertex(port3));
    assertTrue(graph.containsEdge(port6,port4));
    graph.removeEdge(port6,port4);
    assertFalse(graph.containsEdge(port6,port4));
    assertEquals(10,graph.getEdgeCount());
    graph.removeVertex(port5);
    assertEquals(5,graph.getVertexCount());
    assertEquals(7,graph.getEdgeCount());

  }

  /**
   * Shortest Path and getPathCost produce the correct cost and path
   */
  @Test
  public void testGetPathCost(){
    AirportPH port1 = new AirportPH("MAD","WI","Madison");
    AirportPH port2 = new AirportPH("MAF","WI","Belford");
    AirportPH port3 = new AirportPH("MIA","WAS","Miami");
    AirportPH port4 = new AirportPH("CHI","QQ","Chicago");
    AirportPH port5 = new AirportPH("MMP","WI","MemU");
    AirportPH port6 = new AirportPH("BOL","CO","Boulder");

    
    String expected1 = "[MAD, MIA, BOL, CHI]";
    assertEquals(expected1,graph.shortestPath(port1, port4).toString());
    assertEquals(400,graph.getPathCost(port1, port4));
    String expected2 = "[MAD, MIA, BOL]";
    assertEquals(expected2,graph.shortestPath(port1, port6).toString());
    assertEquals(300,graph.getPathCost(port1, port6));

  }

  /**
   * Tests printMap and getDirectVertex work correctly
   */
  @Test
  public void testGetDirectVertex() {
    AirportPH port1 = new AirportPH("MAD","WI","Madison");
    AirportPH port2 = new AirportPH("MAF","WI","Belford");
    AirportPH port3 = new AirportPH("MIA","WAS","Miami");
    AirportPH port4 = new AirportPH("CHI","QQ","Chicago");
    AirportPH port5 = new AirportPH("MMP","WI","MemU");
    AirportPH port6 = new AirportPH("BOL","CO","Boulder");

    graph.printMap(); // print all 11 edge from source to destination
    List<IAirport> result1 = new ArrayList<IAirport>();
    result1 = graph.getDirectVertex("BOL");
    String expected1 = "Airport: MIA, State: WAS, City: Miami";
    String expected2 = "Airport: MIA, State: WAS, City: MIA";
    assertEquals(expected1,result1.get(0).toString());

    List<IAirport> result2 = new ArrayList<IAirport>();
    result2 = graph.getDirectVertex("PQR");
    String expected3 = "Airport: GHI, State: WAS, City: Washington";
  }

  /**
   * Tests Duration and Cost are printed properly based on the
   * flight between two ports
   */
  @Test
  public void testGetCostDuration() {
    AirportPH port1 = new AirportPH("MAD","WI","Madison");
    AirportPH port2 = new AirportPH("MAF","WI","Belford");
    AirportPH port3 = new AirportPH("MIA","WAS","Miami");
    AirportPH port4 = new AirportPH("CHI","QQ","Chicago");
    AirportPH port5 = new AirportPH("MMP","WI","MemU");
    AirportPH port6 = new AirportPH("BOL","CO","Boulder");

    double distance = graph.getPathCost(port1, port6);
    assertEquals(300,distance);
    assertEquals(1.5,graph.getDuration(distance));
    double hours = graph.getDuration(distance);
    assertEquals(150.0,graph.getCost(hours));
  }

  /**
   * ShortestThreePathsHelper and core method work properly and finds 1st, 2nd, and 3rd
   * shortest paths between two ports
   */
  @Test
  public void testShortestThreePaths(){
    AirportPH port1 = new AirportPH("MAD","WI","Madison");
    AirportPH port2 = new AirportPH("MAF","WI","Belford");
    AirportPH port3 = new AirportPH("MIA","WAS","Miami");
    AirportPH port4 = new AirportPH("CHI","QQ","Chicago");
    AirportPH port5 = new AirportPH("MMP","WI","MemU");
    AirportPH port6 = new AirportPH("BOL","CO","Boulder");

    graph.insertEdge(port5, port4, 400);

    assertEquals(2,graph.ShortestThreePathsHelper(port1,port4).size());

    assertEquals(2,graph.ShortestThreePaths(port1,port4).size());

    String expected1 = "[MAD, MIA, BOL, CHI]";
    assertEquals(expected1,graph.ShortestThreePaths(port1,port4).get(0).dataSequence.toString());
    String expected2 = "[MAD, CHI]";
    assertEquals(expected2,graph.ShortestThreePaths(port1,port4).get(1).dataSequence.toString());
    //String expected3 = "[MAD, MIA, MAF, MMP, CHI]";
    assertEquals(400,graph.ShortestThreePaths(port1,port4).get(0).distance);
    assertEquals(500,graph.ShortestThreePaths(port1,port4).get(1).distance);
  }

}