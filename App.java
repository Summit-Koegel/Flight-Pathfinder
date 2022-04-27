/**
 * Runs the flight mapper app
 */
public class App {

    /**
     * main method to run app
     * @param args
     */
    public static void main(String[] args) {
        FlightRouteFrontend f = new FlightRouteFrontend(new FlightRouteBackendFD<IAirport>());
        f.runCommandLoop();
    }
}
