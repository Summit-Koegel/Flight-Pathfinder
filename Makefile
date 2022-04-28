clean: 
	rm *.class

runTests: DataWranglerTests.class
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
