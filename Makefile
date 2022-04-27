runTests: FrontendDeveloperTests.class
	java -jar junit5.jar -cp . --scan-classpath -n FrontendDeveloperTests

FrontendDeveloperTests.class: FrontendDeveloperTests.java FlightRouteFrontend.class TextUITester.class
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

clean:
	rm *.class
