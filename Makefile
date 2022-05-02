runTests: runDataWranglerTests runBackendTests

clean: 
	rm *.class

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --class-path . --scan-classpath

DataWranglerTests.class: DataWranglerTests.java Airport.class FlightLoader.class Flight.class
	javac -cp .:junit5.jar DataWranglerTests.java -Xlint

Airport.class: Airport.java IAirport.class
	javac Airport.java

IAirport.class: IAirport.java
	javac IAirport.java

Flight.class: Flight.java
	javac Flight.java

IFlightLoader.class: IAirport.class IFlightLoader.java
	javac IFlightLoader.java

FlightLoader.class: FlightLoader.java IFlightLoader.class flights.json Flight.class Airport.class IAirport.class
	javac FlightLoader.java
clean:
	rm *.class
compileBackend: 
	javac AirportPL.java
	javac AirportAEPL.java
	javac CS400Graph.java
	javac FlightLoaderPL.java
	javac FlightRouteBackend.java
runBackendTests: compileBackend
	javac -cp .:junit5.jar BackendDeveloperTests.java -Xlint
	java -jar junit5.jar -cp . --scan-classpath -n BackendDeveloperTests

