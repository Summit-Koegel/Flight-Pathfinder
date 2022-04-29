// --== CS400 File Header Information ==--
// Name: Summit Koegel
// Email: skoegel@wisc.edu
// Team: DQ
// TA: Ilay Raz
// Lecturer: Florian Heimerl
// Notes to Grader: <optional extra notes>

/**
 * interface for the Airport class
 *
 * @author charlie jungwirth
 * @auther Austin Wright
 *
 */
//constructor takes (String AirportName, String State, String City)
public interface IAirport {

    /**
     *
     * @return the 3 letter capital letter code for this airport
     *
     */
    public String getAirportCode();

    /**
     *
     * @return state airport is in
     */
    public String getState();

    /**
     *
     * @return the city the airport is in
     */
    public String getCity();

}