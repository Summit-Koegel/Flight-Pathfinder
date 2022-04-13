/**
 * Classes that implement this interface will run a command loop that
 * can be used to map out flight routes
 */
public interface IFlightMapperFrontend {

    // constructor takes IFlightMapperBackend as argument and reads input
    // from System.in

    /**
     * Runs the command loop that enables the user to begin using the
     * program
     */
    public void runCommandLoop();

    /**
     * Prints the display menu with the main options that are repeated
     */
    public void printCommandMenu();

    /**
     * Prints the results of the shortest path search
     */
    public void shortestPathSearch();

    /**
     * Prints the results of all connections from an airport
     */
    public void connectionSearch();

    /**
     * Prints the results after adding a connection between two airports
     */
    public void addConnection();

    /**
     * Prints the results after removing a connection between two airports
     */
    public void removeConnection();
}
