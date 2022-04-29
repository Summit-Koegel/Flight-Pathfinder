runTests: compileTests 
	java -jar junit5.jar -cp . --scan-classpath -n AlgorithmEngineerTests

compileTests: AlgorithmEngineerTests.java
	javac -cp .:junit5.jar AlgorithmEngineerTests.java

clean:
	rm -f *.class
