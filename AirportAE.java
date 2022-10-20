import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class AirportAE implements IAirportAE {

    /**
     * Vertex objects group a data field with an adjacency list of weighted
     * directed edges that lead away from them.
     */
    protected class Vertex {
        public IAirport data; // vertex label or application specific data
        public LinkedList<Edge> edgesLeaving;

        public Vertex(IAirport data) {
            this.data = data;
            this.edgesLeaving = new LinkedList<>();
        }
    }

    /**
     * Edge objects are stored within their source vertex, and group together
     * their target destination vertex, along with an integer weight.
     */
    protected class Edge {
        public Vertex target;
        public int weight;

        public Edge(Vertex target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    protected Hashtable<String, Vertex> vertices; // holds graph verticies, key=data

    public AirportAE() {
        vertices = new Hashtable<>();
    }

    /**
     * Insert a new vertex into the graph.
     * 
     * @param data the data item stored in the new vertex
     * @return true if the data can be inserted as a new vertex, false if it is
     *         already in the graph
     * @throws NullPointerException if data is null
     */
    public boolean insertVertex(IAirport data) {
        if (data == null)
            throw new NullPointerException("Cannot add null vertex");
        String airportName = data.getAirportCode();
        if (vertices.containsKey(airportName))
            return false; // duplicate values are not allowed
        vertices.put(airportName, new Vertex(data));
        return true;
    }

    /**
     * Remove a vertex from the graph.
     * Also removes all edges adjacent to the vertex from the graph (all edges
     * that have the vertex as a source or a destination vertex).
     * 
     * @param data the data item stored in the vertex to remove
     * @return true if a vertex with *data* has been removed, false if it was not in
     *         the graph
     * @throws NullPointerException if data is null
     */
    public boolean removeVertex(IAirport data) {
        if (data == null)
            throw new NullPointerException("Cannot remove null vertex");
        String airportName = data.getAirportCode();
        Vertex removeVertex = vertices.get(airportName);
        if (removeVertex == null)
            return false; // vertex not found within graph
        // search all vertices for edges targeting removeVertex
        for (Vertex v : vertices.values()) {
            Edge removeEdge = null;
            for (Edge e : v.edgesLeaving)
                if (e.target == removeVertex)
                    removeEdge = e;
            // and remove any such edges that are found
            if (removeEdge != null)
                v.edgesLeaving.remove(removeEdge);
        }
        // finally remove the vertex and all edges contained within it
        return vertices.remove(airportName) != null;
    }

    /**
     * Insert a new directed edge with a positive edge weight into the graph.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @param weight the weight for the edge (has to be a positive integer)
     * @return true if the edge could be inserted or its weight updated, false
     *         if the edge with the same weight was already in the graph
     * @throws IllegalArgumentException if either source or target or both are not
     *                                  in the graph,
     *                                  or if its weight is < 0
     * @throws NullPointerException     if either source or target or both are null
     */
    public boolean insertEdge(IAirport source, IAirport target, int weight) {
        if (source == null || target == null)
            throw new NullPointerException("Cannot add edge with null source or target");
        String airportS = source.getAirportCode();
        String airportT = target.getAirportCode();
        Vertex sourceVertex = this.vertices.get(airportS);
        Vertex targetVertex = this.vertices.get(airportT);
        if (sourceVertex == null || targetVertex == null)
            throw new IllegalArgumentException("Cannot add edge with vertices that do not exist");
        if (weight < 0)
            throw new IllegalArgumentException("Cannot add edge with negative weight");
        // handle cases where edge already exists between these verticies
        for (Edge e : sourceVertex.edgesLeaving)
            if (e.target == targetVertex) {
                if (e.weight == weight)
                    return false; // edge already exists
                else
                    e.weight = weight; // otherwise update weight of existing edge
                return true;
            }
        // otherwise add new edge to sourceVertex
        sourceVertex.edgesLeaving.add(new Edge(targetVertex, weight));
        return true;
    }

    /**
     * Remove an edge from the graph.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge could be removed, false if it was not in the graph
     * @throws IllegalArgumentException if either source or target or both are not
     *                                  in the graph
     * @throws NullPointerException     if either source or target or both are null
     */
    public boolean removeEdge(IAirport source, IAirport target) {
        if (source == null || target == null)
            throw new NullPointerException("Cannot remove edge with null source or target");
        String airportS = source.getAirportCode();
        String airportT = target.getAirportCode();
        Vertex sourceVertex = this.vertices.get(airportS);
        Vertex targetVertex = this.vertices.get(airportT);
        if (sourceVertex == null || targetVertex == null)
            throw new IllegalArgumentException("Cannot remove edge with vertices that do not exist");
        // find edge to remove
        Edge removeEdge = null;
        for (Edge e : sourceVertex.edgesLeaving)
            if (e.target == targetVertex)
                removeEdge = e;
        if (removeEdge != null) { // remove edge that is successfully found
            sourceVertex.edgesLeaving.remove(removeEdge);
            return true;
        }
        return false; // otherwise return false to indicate failure to find
    }

    /**
     * Check if the graph contains a vertex with data item *data*.
     * 
     * @param data the data item to check for
     * @return true if data item is stored in a vertex of the graph, false otherwise
     * @throws NullPointerException if *data* is null
     */
    public boolean containsVertex(IAirport data) {
        if (data == null)
            throw new NullPointerException("Cannot contain null data vertex");
        String airportName = data.getAirportCode();
        return vertices.containsKey(airportName);
    }

    /**
     * Check if edge is in the graph.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge is in the graph, false if it is not in the graph
     * @throws NullPointerException if either source or target or both are null
     */
    public boolean containsEdge(IAirport source, IAirport target) {
        if (source == null || target == null)
            throw new NullPointerException("Cannot contain edge adjacent to null data");
        String airportS = source.getAirportCode();
        String airportT = target.getAirportCode();
        Vertex sourceVertex = vertices.get(airportS);
        Vertex targetVertex = vertices.get(airportT);
        if (sourceVertex == null)
            return false;
        for (Edge e : sourceVertex.edgesLeaving)
            if (e.target == targetVertex)
                return true;
        return false;
    }

    /**
     * Return the weight of an edge.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return the weight of the edge (0 or positive integer)
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or
     *                                  both are not in the graph
     * @throws NullPointerException     if either sourceVertex or targetVertex or
     *                                  both are null
     * @throws NoSuchElementException   if edge is not in the graph
     */
    public int getWeight(IAirport source, IAirport target) {
        if (source == null || target == null)
            throw new NullPointerException("Cannot contain weighted edge adjacent to null data");
        String airportS = source.getAirportCode();
        String airportT = target.getAirportCode();
        Vertex sourceVertex = vertices.get(airportS);
        Vertex targetVertex = vertices.get(airportT);
        if (sourceVertex == null || targetVertex == null)
            throw new IllegalArgumentException("Cannot retrieve weight of edge between vertices that do not exist");
        for (Edge e : sourceVertex.edgesLeaving)
            if (e.target == targetVertex)
                return e.weight;
        throw new NoSuchElementException("No directed edge found between these vertices");
    }

    /**
     * Return the number of edges in the graph.
     * 
     * @return the number of edges in the graph
     */
    public int getEdgeCount() {
        int edgeCount = 0;
        for (Vertex v : vertices.values())
            edgeCount += v.edgesLeaving.size();
        return edgeCount;
    }

    /**
     * Return the number of vertices in the graph
     * 
     * @return the number of vertices in the graph
     */
    public int getVertexCount() {
        return vertices.size();
    }

    /**
     * Check if the graph is empty (does not contain any vertices or edges).
     * 
     * @return true if the graph does not contain any vertices or edges, false
     *         otherwise
     */
    public boolean isEmpty() {
        return vertices.size() == 0;
    }

    /**
     * Path objects store a discovered path of vertices and the overal distance of
     * cost
     * of the weighted directed edges along this path. Path objects can be copied
     * and extended
     * to include new edges and verticies using the extend constructor. In
     * comparison to a
     * predecessor table which is sometimes used to implement Dijkstra's algorithm,
     * this
     * eliminates the need for tracing paths backwards from the destination vertex
     * to the
     * starting vertex at the end of the algorithm.
     */
    protected class Path implements Comparable<Path> {
        public Vertex start; // first vertex within path
        public int distance; // sumed weight of all edges in path
        public List<String> dataSequence; // ordered sequence of data from vertices in path
        public Vertex end; // last vertex within path

        /**
         * Creates a new path containing a single vertex. Since this vertex is both
         * the start and end of the path, it's initial distance is zero.
         * 
         * @param start is the first vertex on this path
         */
        public Path(Vertex start) {
            this.start = start;
            this.distance = 0;
            this.dataSequence = new LinkedList<>();
            this.dataSequence.add(start.data.getAirportCode());
            this.end = start;
        }

        /**
         * This extension constructor makes a copy of the path passed into it as an
         * argument
         * without affecting the original path object (copyPath). The path is then
         * extended
         * by the Edge object extendBy.
         * 
         * @param copyPath is the path that is being copied
         * @param extendBy is the edge the copied path is extended by
         */
        public Path(Path copyPath, Edge extendBy) {
            this.start = copyPath.start;
            this.dataSequence = new LinkedList<>(copyPath.dataSequence); // Creates a deep copy
            this.dataSequence.add(extendBy.target.data.getAirportCode());
            this.distance = copyPath.distance + extendBy.weight;
            this.end = extendBy.target;
        }

        /**
         * Allows the natural ordering of paths to be increasing with path distance.
         * When path distance is equal, the string comparison of end vertex data is used
         * to break ties.
         * 
         * @param other is the other path that is being compared to this one
         * @return -1 when this path has a smaller distance than the other,
         *         +1 when this path has a larger distance that the other,
         *         and the comparison of end vertex data in string form when these
         *         distances are tied
         */
        public int compareTo(Path other) {
            int cmp = this.distance - other.distance;
            if (cmp != 0)
                return cmp; // use path distance as the natural ordering
            // when path distances are equal, break ties by comparing the string
            // representation of data in the end vertex of each path
            return this.end.data.getAirportCode().toString().compareTo(other.end.data.getAirportCode().toString());
        }
    }

    /**
     * Uses Dijkstra's shortest path algorithm to find and return the shortest path 
     * between two vertices in this graph: start and end. This path contains an ordered list
     * of the data within each node on this path, and also the distance or cost of all edges
     * that are a part of this path.
     * @param start data item within first node in path
     * @param end data item within last node in path
     * @return the shortest path from start to end, as computed by Dijkstra's algorithm
     * @throws NoSuchElementException when no path from start to end can be found,
     *     including when no vertex containing start or end can be found
     */
    protected Path dijkstrasShortestPath(IAirport start, IAirport end) {

        String airportS = start.getAirportCode();
        String airportT = end.getAirportCode();
        Vertex startVertex = vertices.get(airportS); //Vertex of source airport
        Vertex endVertex = vertices.get(airportT); // Vertex of target airport
        Path startVert = new Path(vertices.get(airportS));

        // Start or end vertex does not exist in graph
        if (startVertex == null || endVertex == null) {
            throw new NoSuchElementException("Start or end is not in the graph!");
        }

        Hashtable<String, Boolean> shortest = new Hashtable<>();

        for(Vertex vert : vertices.values()){
            shortest.put(vert.data.getAirportCode(), false);
        }

        Set<Vertex> visited = new HashSet<>(); // What ports have been visitied
        Path startingPath = new Path(startVertex);
        PriorityQueue<Path> paths = new PriorityQueue<>(); // Shortest paths
        paths.offer(startVert);
        shortest.put(airportS, true);
        Path endGoal = null;
        int dist = Integer.MAX_VALUE;
        //paths.add(startingPath);

    while (!paths.isEmpty()) {
        Path current = paths.poll();
        Vertex end_vertex = current.end;
  
        if (!shortest.get(end_vertex.data.getAirportCode())) {
            shortest.put(end_vertex.data.getAirportCode(), true);
        }
  
        LinkedList<Edge> neighbor = end_vertex.edgesLeaving;
        for (int i = 0; i < neighbor.size(); i++) {
          Edge edge = neighbor.get(i);
          Vertex destination = edge.target;
  
          if (!shortest.get(destination.data.getAirportCode())) {
            Path nextPath = new Path(current, edge);
            paths.offer(nextPath);
  
            if (dist > nextPath.distance && destination.data.getAirportCode().equals(end.getAirportCode())) {
              endGoal = nextPath;
              dist = endGoal.distance;
            }
          }
        }
      }
      
      if (endGoal == null) {
        throw new NoSuchElementException("no path from start to end can be found");
      }
      return endGoal;

      /*

        // Loop until a path to the target is found, or all paths have been searched
        while (paths.size() > 0 && paths.peek().end != endVertex) {
            // Remove the shortest current path
            Path currentPath = paths.poll();

            // Mark the end of the path as visited
            visited.add(currentPath.end);

            for (Edge edge : currentPath.end.edgesLeaving) {
                // Skip edges to already visited vertices
                if (visited.contains(edge.target)) continue;

                // Add all paths that extend the current shortest path by one edge
                paths.add(new Path(currentPath, edge));
            }
        }

        // If no paths are in the queue, or the shortest path does not end at target, no valid path was found
        if (paths.size() == 0 || paths.peek().start != startVertex || paths.peek().end != endVertex) {
            throw new NoSuchElementException("No path from start to end found!");
        }

        // Return the shortest path in the queue, which goes from start to end
        
        list.add(paths.poll());
        list.add(paths.poll());
        list.add(paths.poll());

        return list;
        */
    }

    /**
     * Returns the shortest path between start and end.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     * 
     * @param start the data item in the starting vertex for the path
     * @param end   the data item in the destination vertex for the path
     * @return list of data item in vertices in order on the shortest path between
     *         vertex
     *         with data item start and vertex with data item end, including both
     *         start and end
     * @throws NoSuchElementException when no path from start to end can be found
     *                                including when no vertex containing start or
     *                                end can be found
     */
    public List<String> shortestPath(IAirport start, IAirport end) {
        return dijkstrasShortestPath(start, end).dataSequence;
    }

    /**
     * Returns the cost of the path (sum over edge weights) between start and end.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     * 
     * @param start the data item in the starting vertex for the path
     * @param end   the data item in the end vertex for the path
     * @return the cost of the shortest path between vertex with data item start
     *         and vertex with data item end, including all edges between start and
     *         end
     * @throws NoSuchElementException when no path from start to end can be found
     *                                including when no vertex containing start or
     *                                end can be found
     */
    public int getPathCost(IAirport start, IAirport end) {
        return dijkstrasShortestPath(start, end).distance;
    }

    /**
     * user give a source airport, then list all the vertex direct to the source
     * vertex
     * 
     * @param source the data item contained in the source vertex for the edge
     * @return list of data item in vertices which direct to the source vertex
     */
    @Override
    public List<IAirport> getDirectVertex(String airport) {
        List<IAirport> airportsList = new ArrayList<IAirport>();

        for (Vertex vert : vertices.values()) {
            for (Edge edge : vert.edgesLeaving)
                if (edge.target.data.getAirportCode().equals(airport))
                    airportsList.add(vert.data);
        }

        return airportsList;
    }


    /**
     * give user the duration about this flight
     * 
     * @param distance the distance about this flight
     * @return the duration of flight
     */
    @Override
    public double getDuration(double distance) {
        return distance/200;
    }

    /**
     * give the user the cost of this flight
     * 
     * @param hour the duration about this transport
     * @return the cost of flight
     */
    @Override
    public double getCost(double hour) {
        return hour * 100;
    }

    /**
     * display all the city can connect to which city let user can easy to check
     */
    @Override
    public void printMap() {

        Set<String> set = this.vertices.keySet();

        for (String name : set) {

            int num = vertices.get(name).edgesLeaving.size();
            for (int i = 0; i < num; i++) {
                String namePartner = vertices.get(name).edgesLeaving.get(i).target.data.getAirportCode();
                System.out.println(name + " --> " + namePartner);
            }
        }
    }

    protected List<Path> ShortestThreePathsHelper(IAirport start, IAirport end) {

        String airportS = start.getAirportCode();
        String airportT = end.getAirportCode();
        Vertex startVertex = vertices.get(airportS);
        Vertex endVertex = vertices.get(airportT);
        Path startVert = new Path(vertices.get(airportS));

        // Start or end vertex does not exist in graph
        if (startVertex == null || endVertex == null) {
            throw new NoSuchElementException("Start or end is not in the graph!");
        }

        Hashtable<String, Boolean> shortest = new Hashtable<>();

        for(Vertex vert : vertices.values()){
            shortest.put(vert.data.getAirportCode(), false);
        }

        Set<Vertex> visited = new HashSet<>();
        List<Path> all = new ArrayList<Path>();
        Path startingPath = new Path(startVertex);
        PriorityQueue<Path> paths = new PriorityQueue<>();
        paths.offer(startVert);
        shortest.put(airportS, true);
        Path endGoal = null;
        int dist = Integer.MAX_VALUE;
        //paths.add(startingPath);

    while (!paths.isEmpty()) {
        Path current = paths.poll();
        Vertex end_vertex = current.end;
  
        if (!shortest.get(end_vertex.data.getAirportCode())) {
            shortest.put(end_vertex.data.getAirportCode(), true);
        }
  
        LinkedList<Edge> neighbor = end_vertex.edgesLeaving;
        for (int i = 0; i < neighbor.size(); i++) {
          Edge edge = neighbor.get(i);
          Vertex destination = edge.target;
  
          if (!shortest.get(destination.data.getAirportCode())) {
            Path nextPath = new Path(current, edge);
            paths.offer(nextPath);
  
            if (destination.data.getAirportCode().equals(end.getAirportCode())) {
              endGoal = nextPath;
              all.add(endGoal);
            }
          }
        }
      }
      
      if (endGoal == null) {
        throw new NoSuchElementException("no path from start to end can be found");
      }
      return all;
    }

   /**
   * user give a source airport and destination airport, then list last three shortest path
   * @param source the airport you start
   * @param destination the airport you want to arrive
   * @return array of Linkedlist for last three shortest path from source to destination
   */
    public List<List<IAirport>> ShortestThreePaths(IAirport start, IAirport end) {
        
        List<Path> all = new ArrayList<Path>();
        all = ShortestThreePathsHelper(start, end);

        Collections.sort(all);
        List<List<IAirport>> finalPath = new ArrayList<List<IAirport>>();
        //int counter = 0;

        List<IAirport> l = new ArrayList<IAirport>();

        for(int i = 0; i < 3; i++) {
            for(String s : all.get(i).dataSequence) {
                //System.out.println(all.get(i).dataSequence);
                IAirport ap = new AirportPH(s, "", "");
                l.add(ap);
            }
            finalPath.add(l);
            //System.out.println(finalPath);
        }

        return finalPath;


        /*
        for (int i = 0; i < all.size(); i++) {
            finalPath.add(all.get(i).dataSequence);
            counter += 1;
            if (counter == 3) {
                break;
            }
        }

        return finalPath;
        */
    }

@Override
public List<IAirport> getDirectVertex(IAirport source) {
    // TODO Auto-generated method stub
    return null;
}




}
