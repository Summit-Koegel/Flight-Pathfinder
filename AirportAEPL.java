// --== CS400 Project [NUMBER] File Header ==--
// Name: Aaryush Gupta
// CSL Username: aaryush
// Email: agupta276@wisc.edu
// Lecture #: 004
// Notes to Grader: <Notes>


import java.util.ArrayList;
import java.util.List;

public class AirportAEPL extends CS400Graph<String> implements IAirportAE{

  protected CS400Graph<String> graph = new CS400Graph<String>();

  @Override public List<String> getDirectVertex(String airport) {
    List<String> result = new ArrayList<>();
    graph.insertVertex("LAX");
    graph.insertVertex("ORD");
    graph.insertEdge("ORD", "LAX", 5);
    for (CS400Graph<String>.Edge edge : graph.vertices.get(airport).edgesLeaving) {
      result.add(edge.target.data);
    }
    return result;
  }

  @Override public ArrayList<String[]> getLastThreeShortestPath(String source, String destination) {
    ArrayList<String[]> result = new ArrayList<String[]>();
    String[] path1 = new String[]{"ORD", "LAX"};
    String[] path2 = new String[]{"ORD", "BOM"};
    String[] path3 = new String[]{"BOM", "LAX"};
    result.add(path1);
    result.add(path2);
    result.add(path3);
    return result;
  }

  @Override public double getduration(double distance) {
    return 0;
  }

  @Override public double getcost(double hour) {
    return 0;
  }

  @Override public void printMap() {

  }
}

