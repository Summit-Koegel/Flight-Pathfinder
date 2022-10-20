public class AirportPH implements IAirport{

    private String code;
    private String state;
    private String city;

    public AirportPH(String code, String state, String city){
        this.code = code;
        this.state = state;
        this.city = city;
    }

    @Override
    public String getAirportCode() {
        return this.code;
    }

    @Override
    public String getState() {
        return this.state;
    }

    @Override
    public String getCity() {
        return this.city;
    }

    public String toString(){
        return (getAirportCode());
    }
    
}
