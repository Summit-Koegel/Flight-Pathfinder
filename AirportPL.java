public class AirportPL implements IAirport {
  String code;
  String state;
  String city;

  public AirportPL(String code, String state, String city) {
    this.code = code;
    this.state = state;
    this.city = city;
  }

  @Override public String getAirportCode() {
    return code;
  }

  @Override public String getState() {
    return state;
  }

  @Override public String getCity() {
    return city;
  }
}

