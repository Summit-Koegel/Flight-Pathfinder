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
