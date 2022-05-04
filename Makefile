runTests: runDataWranglerTests runFrontendTests runAlgorithmEngineerTests runBackendTests

run: DataWranglerTests.class FrontendDeveloperTests.class compileBackend compileTests
	javac App.java
	java App

clean: 
	rm *.class

runDataWranglerTests: DataWranglerTests.class
	java -jar junit5.jar --class-path . --scan-classpath

DataWranglerTests.class: DataWranglerTests.java Airport.class FlightLoader.class Flight.class FlightRouteBackendDW.class FlightRouteFrontend.class
	javac -cp .:junit5.jar DataWranglerTests.java -Xlint

FlightRouteBackendDW.class: FlightRouteBackendDW.java IFlightRouteBackend.class
	javac FlightRouteBackendDW.java

IFlightRouteBackend.class: IFlightRouteBackend.java
	javac IFlightRouteBackend.java

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
compileBackend: 
	javac AirportPL.java
	javac AirportAEPL.java
	javac CS400Graph.java
	javac FlightLoaderPL.java
	javac FlightRouteBackend.java
runBackendTests: compileBackend
	javac -cp .:junit5.jar BackendDeveloperTests.java -Xlint
	java -jar junit5.jar -cp . --scan-classpath -n BackendDeveloperTests

runFrontendTests: FrontendDeveloperTests.class
		java -jar junit5.jar -cp . --scan-classpath -n FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java FlightRouteFrontend.class TextUITester.class Airport.java FlightLoader.java Flight.java FlightRouteBackend.java IFlightLoader.java 
	javac Airport.java
	javac FlightLoader.java
	javac Flight.java
	javac FlightRouteBackend.java
	javac IFlightLoader.java
	javac -cp .:junit5.jar FrontendDeveloperTests.java -Xlint

TextUITester.class: TextUITester.java
	javac TextUITester.java

FlightRouteFrontend.class: FlightRouteFrontend.java AirportFD.class FlightRouteBackendFD.class
	javac FlightRouteFrontend.java

AirportFD.class: AirportFD.java IAirport.java
	javac IAirport.java
	javac AirportFD.java

FlightRouteBackendFD.class: FlightRouteBackendFD.java IFlightRouteBackend.java
	javac IFlightRouteBackend.java
	javac FlightRouteBackendFD.java

runAlgorithmEngineerTests: compileTests 
	java -jar junit5.jar -cp . --scan-classpath -n AlgorithmEngineerTests

compileTests: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

