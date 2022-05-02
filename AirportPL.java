// --== CS400 Project [NUMBER] File Header ==--
// Name: Aaryush Gupta
// CSL Username: aaryush
// Email: agupta276@wisc.edu
// Lecture #: 004
// Notes to Grader: <Notes>


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

