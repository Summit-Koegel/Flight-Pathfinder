import java.util.List;
import java.util.Scanner;

public class FlightRouteFrontend implements IFlightRouteFrontend{

    IFlightRouteBackend<IAirport> backend;
    Scanner sc;

    /**
     * Creates a new frontend to use in the app
     * @param backend is the backend to reference
     */
    public FlightRouteFrontend(IFlightRouteBackend<IAirport> backend) {
        this.backend = backend;
        sc = new Scanner(System.in);
    }

    /**
     * Runs the main command loop where the user can make
     * a choice on what to do next
     */
    @Override
    public void runCommandLoop() {

        boolean loop = true;

        System.out.println("STARTING FLIGHT MAPPER");
        System.out.println("—--------------------------------------");

        while(loop) {
            printCommandMenu();
            String input = sc.nextLine();

            switch(input.toLowerCase().strip()) {
                case "f": // shortest path
                    shortestPathSearch();
                    break;
                case "l": // connections
                    connectionSearch();
                    break;
                case "a": // add connection
                    addConnection();
                    break;
                case "r": // remove connection
                    removeConnection();
                    break;
                case "q": // quit
                    loop = false;
                    break;
                default: // invalid input
                    System.out.println("INVALID INPUT: PLEASE TRY AGAIN");
                    break;
            }
        }
    }

    /**
     * Helper method to print the main command menu to the screen
     */
    @Override
    public void printCommandMenu() {
        System.out.println();
        System.out.println("PLEASE SELECT AN OPTION:");
        System.out.println("[F]IND PATH BETWEEN TWO AIRPORTS");
        System.out.println("[L]IST FLIGHTS FROM AN AIRPORT");
        System.out.println("[A]DD FLIGHT CONNECTION");
        System.out.println("[R]EMOVE FLIGHT CONNECTION");
        System.out.println("[Q]UIT");
    }

    /**
     * Gets input from the user and displays the result of the
     * shortest path search between the desired airports
     */
    @Override
    public void shortestPathSearch() {
        sc = new Scanner(System.in);
        // gets desired airports
        System.out.println("PLEASE TYPE 3 LETTER CODE OF DESTINATION AIRPORT:");
        String dest = sc.nextLine();
        System.out.println("PLEASE TYPE 3 LETTER CODE OF SOURCE AIRPORT:");
        String src = sc.nextLine();
        // sends data to the backend and stores the result
        IAirport destA = new AirportFD(dest, "", "");
        IAirport srcA = new AirportFD(src, "", "");
        List<IAirport> path = backend.findFlight(srcA, destA);
        // display results
        if (path.size() != 0) {
            System.out.println("FLIGHT FOUND:");
        } else {
            System.out.println("NO FLIGHT FOUND");
        }
        for(IAirport ap : path) {
            System.out.print(ap.getAirportCode() + (ap.getAirportCode().equals(dest)?"":"--->"));
        }
    }

    /**
     * Gets input from the user and displays all direct flights
     * from the desired airport
     */
    @Override
    public void connectionSearch() {
        sc = new Scanner(System.in);
        // get desired airport
        System.out.println("PLEASE ENTER A CITY:");
        String t = sc.nextLine().strip();
        IAirport a = new AirportFD("", "", t);
        // send data to backend
        List<IAirport> l = backend.findDirectFlightsOutOf(a);
        // display results
        if (l.size() != 0) {
            System.out.println("FLIGHTS AVAILABLE:");
        } else {
            System.out.println("NO FLIGHTS FOUND");
        }
        for(IAirport ap : l) {
            System.out.print(ap.getAirportCode() + (l.indexOf(ap)==l.size()-1?"":", "));
        }
    }

    /**
     * Gets input from the user and allows them to make
     * a connection between two airports
     */
    @Override
    public void addConnection() {
        sc = new Scanner(System.in);
        // gets data from user
        System.out.println("PLEASE TYPE 3 LETTER CODE OF DESTINATION AIRPORT:");
        String dest = sc.nextLine();
        System.out.println("PLEASE TYPE 3 LETTER CODE OF SOURCE AIRPORT:");
        String src = sc.nextLine();
        // sends data to backend and displays whether the result was a success
        IAirport destA = new AirportFD(dest, "", "");
        IAirport srcA = new AirportFD(src, "", "");
        if(((FlightRouteBackendFD)backend).makeConnection(srcA, destA))
            System.out.println("NEW CONNECTION MADE FROM " + src + " TO " + dest);
        else
            System.out.println("CONNECTION FAILED");
    }

    /**
     * Gets input from the user and allows them to remove
     * a connection between two airports
     */
    @Override
    public void removeConnection() {
        sc = new Scanner(System.in);
        // gets data from user
        System.out.println("PLEASE TYPE 3 LETTER CODE OF DESTINATION AIRPORT:");
        String dest = sc.nextLine();
        System.out.println("PLEASE TYPE 3 LETTER CODE OF SOURCE AIRPORT:");
        String src = sc.nextLine();
        // sends data to backend and displays whether the result was a success
        IAirport destA = new AirportFD(dest, "", "");
        IAirport srcA = new AirportFD(src, "", "");
        if(((FlightRouteBackendFD)backend).removeConnection(srcA, destA))
            System.out.println("CONNECTION REMOVED FROM " + src + " TO " + dest);
        else
            System.out.println("CONNECTION REMOVAL FAILED");
    }
}
