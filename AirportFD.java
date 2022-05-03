/**
 * Hard coded implementation of Airport.java
 */
public class AirportFD implements IAirport {

    private String code;
    private String state;
    private String city;

    /**
     * Makes a new AirportFD
     */
    public AirportFD(String code, String state, String city) {
        this.code = code;
        this.state = state;
        this.city = city;
    }

    /**
     * Gets the 3 letter code of the airport
     */
    @Override
    public String getAirportCode() {
        return code;
    }

    /**
     * Gets the state of the airport
     */
    @Override
    public String getState() {
        return state;
    }

    /**
     * Gets the city of the airport
     */
    @Override
    public String getCity() {
        return city;
    }

}
