Authorization Engine for ISO8583

To build: mvn clean install
unit tests include the sample data from the specification document

To run:
java -jar target/authengine-1-jar-with-dependencies.jar

Input data can be specified with values on the command line,
java -jar target/authengine-1-jar-with-dependencies.jar 0100e016411111111111111112250000001000

from a file (ucing the -i option)
java -jar target/authengine-1-jar-with-dependencies.jar -f input.txt

or if no command line arguments exist then the input will be read from stdin
java -jar target/authengine-1-jar-with-dependencies.jar



