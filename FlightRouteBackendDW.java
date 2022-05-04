import java.util.List;

/**
 * 
 * @author charlie jungwirth
 * backend placeholder used for DataWranglerTest's additional tests on frontend
 * @param <T>
 */
public class FlightRouteBackendDW<T> implements IFlightRouteBackend<IAirport>{

	
	@Override
	public void loadFlights() {
		// Don't need
		
	}

	@Override
	public List<IAirport> findFlight(IAirport start, IAirport end) {
		// Don't need
		return null;
	}

	@Override
	public List<IAirport> findAirportsInCity(String city) {
		// Don't need
		return null;
	}

	@Override
	public List<IAirport> findAirportsInState(String state) {
		// Don't need
		return null;
	}

	@Override
	public List<IAirport> findDirectFlightsOutOf(IAirport city) {
		// Don't need
		return null;
	}

	@Override
	public void showMap() {
		// Don't need
		
	}

	@Override
	public boolean makeConnection(IAirport start, IAirport end) {
		//just returning true for test unless start has FAL code
		if(start.getAirportCode().equals("FAL"))return false;
		return true;
	}

	@Override
	public boolean removeConnection(IAirport start, IAirport end) {
		// Don't need
		return false;
	}

	@Override
	public List<List<IAirport>> threeShortestPaths(IAirport start, IAirport end) {
		// Don't need
		return null;
	}

}
