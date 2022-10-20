import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

/**
 * 
 * @author charlie jungwirth
 *
 * loads all flights in a provided flights.json file, this file has to be
 * formatted with flight objects like
 *{
 *	"Origin_airport":"PDX"
 *	"Destination_airport":"RDM"
 *	"Origin_city":"Portland, OR"
 *	"Destination_city":"Bend, OR"
 *	"Distance":116
 *}
 *	some other formats will  work, but the interperatation from csvTojson.class will definitely work
 * spacing can be a little different, and order of fields can be different
 *  the file has to be named flights.json and be in same folder
 *  This class also contains methods to get info about the flights
 */
public class FlightLoader implements IFlightLoader {
	private Hashtable<String, IAirport> allPorts;//hashtable of all airports
	private ArrayList<Flight> allFlights;//Flight objs
	private ArrayList<IAirport> allPortsList;//list of all airports
	private ArrayList<Integer> dists;//distance of each flight, corresponds to connects
	private ArrayList<IAirport[]> connects;//[origin,destination] of all loaded flights
	private Hashtable<String, Flight> uniqueFlights;
	/**
	 * Loads all airport flight paths in [origin, destination] form
	 * 
	 */
	@Override
	public ArrayList<IAirport[]> loadConnections() {
		return connects;
		
	}

	/**
	 * Loads all airport flight paths distances
	 * corresponds to loadConnections
	 */
	@Override
	public ArrayList<Integer> loadDistances() {
		return dists;
	}
	
	/**
	 * get array list of all flights
	 * @return all flights in Flight format
	 */
	public ArrayList<Flight> loadFlights(){
		return allFlights;
	}
	
	/**
	 * All of the airports with key of its 3 digit code
	 * ex chicago Airport is ORD
	 * @return hash table with code, airport value pairs
	 */
	public Hashtable<String, IAirport> allAirportsHash(){
		return allPorts;
	}
	
	/**
	 * all airports
	 * @return all airports in file
	 */
	public ArrayList<IAirport> allAirportsList(){
		return allPortsList;
	}
	
	/**
	 * constructor for class
	 * reads from file and initializes data
	 * @throws FileNotFoundException if flights.json isn't in folder
	 */
	public FlightLoader() throws FileNotFoundException {
		String filep = "flights.json";
		Scanner scan = new Scanner(new File(filep));
		
		//initialize data fields
		allFlights = new ArrayList<Flight>();
		allPorts= new Hashtable<String, IAirport>();
		allPortsList = new ArrayList<IAirport>();
		connects = new ArrayList<IAirport[]>();
		dists = new ArrayList<Integer>();
		uniqueFlights = new Hashtable<String, Flight>();
		
		while(scan.hasNext()) {
			String temp = scan.next();
			if(temp.contains("{")) {
				//making a new flight
				loadFlight(scan);
			}
			
		}
		
	}
	
	
	/**
	 * loads a flight in, call reading { in scanner
	 * @param scan
	 * @return origincode:destcode:distance
	 * @throws exception if no } to end object
	 */
	private String loadFlight(Scanner scan) {
		Airport exOrig = new Airport();//origin of flight
		Airport exDest = new Airport();//destination of flight
		String current = scan.nextLine();
		Flight curFl = new Flight();
		
		while(!current.contains("}")) {
			String[] pair =current.split(":");
			if(pair.length ==2) {
				pair[0] =pair[0].replaceAll("\t","");//remove tabs
				pair[0] =pair[0].replaceAll(" ","");//remove space
				pair[0] = pair[0].replaceAll(""+(char)34,"");//remove quotes
				
				//load in data read in line
				switch (pair[0]) {
					case "Destination_city":
						pair[1] = pair[1].substring(pair[1].indexOf((char)34)+1);
						pair[1] = pair[1].substring(0,pair[1].indexOf((char)34));
						exDest.city = pair[1].substring(0, pair[1].indexOf(","));
						exDest.state = pair[1].substring(pair[1].indexOf(", ")+2);
					
					break;
					
					case "Distance":
						pair[1] = pair[1].replaceAll(" ", "");
						curFl.distance = Integer.parseInt(pair[1]);
						
					break;
					
					case "Origin_airport":
						pair[1] = pair[1].replaceAll(" ", "");
						pair[1] = pair[1].replaceAll(""+(char)34, "");
						exOrig.name = pair[1];
			
						
					break;
					
					case "Destination_airport":
						pair[1] = pair[1].replaceAll(" ", "");
						pair[1] = pair[1].replaceAll(""+(char)34, "");
						exDest.name = pair[1];
						
					break;
					case "Origin_city":
						pair[1] = pair[1].substring(pair[1].indexOf((char)34)+1);
						pair[1] = pair[1].substring(0,pair[1].indexOf((char)34));
						exOrig.city = pair[1].substring(0, pair[1].indexOf(","));
						exOrig.state = pair[1].substring(pair[1].indexOf(", ")+2);
						
					break;
				
					default:
					break;
				
				
				
				}
			}
			current = scan.nextLine();
		}
		
		//check if new airport is involved
		if(!allPorts.containsKey(exOrig.getAirportCode())) {
			allPorts.put(exOrig.getAirportCode(), exOrig);
			allPortsList.add(exOrig);
		}
		if(!allPorts.containsKey(exDest.getAirportCode())) {
			allPorts.put(exDest.getAirportCode(), exDest);
			allPortsList.add(exDest);
			
		}
		
		//update fields
		curFl.origin = exOrig;
		curFl.destination = exDest;
		if(!uniqueFlights.containsKey(exOrig.getAirportCode()+exDest.getAirportCode())) {
			allFlights.add(curFl);
			connects.add(new IAirport[] {exOrig, exDest});
			dists.add(curFl.distance);
			uniqueFlights.put(exOrig.getAirportCode()+exDest.getAirportCode(), curFl);
		}
		
		
		
		return exOrig.getAirportCode()+":"+exDest.getAirportCode()+":"+curFl.distance;
		
	}

}
