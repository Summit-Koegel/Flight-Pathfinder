// --== CS400 File Header Information ==--
// Name: Charles Jungwirth
// Email: crjungwirth@wisc.edu
// Team: DQ
// TA: Ilay Raz
// Lecturer: Florian Heimerl
// Notes to Grader: Allows multiple options for loading flights, flight objects optional
//	Data file generated from csvTojson.java
//	initial csv file is very big so I didn't put it on google machine
// also are some duplicate paths on csv

/**
 * stores data associated with an airport
 * 
 * @author charlie jungwirth
 *
 */
public class Airport implements IAirport{
	
	public String name = "NAN";
	public String state ="NAN";
	public String city ="NAN";
    /**
     *	ex ORD
     * @return the 3 letter capital letter code for this airport
     *
     */
    public String getAirportCode() {
    	return name;
    }

    /**
     * in two letter format ex: WI
     * @return state airport is in
     */
    public String getState() {
    	return state;
    }
    

    /**
     *
     * @return the city the airport is in
     */
    public String getCity() {
    	return city;
    }
    
    public Airport(String airportName, String airState, String airCity) {
    	name = airportName;
    	state = airState;
    	city = airCity;
    }
    
    public Airport() {
    	
    }
}
