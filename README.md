## Account Validator

Account Validator is a spring boot rest service. It validates the account based on the account number.

## Setup and build
Building this project requires maven and JDK 1.8 and above installed.

## Run
Use the following maven command to run this project. 

### Getting Started
##  Get the source code
Make a directory for account validator. Clone or download and extract the account validator in that directory.
git clone https://github.com/SahdevKamboj/account-validator.git

cd account-validator

mvn clean

mvn install

## Running the service locally with active profiles dev 

The "mvn spring-boot:run -Dspring-boot.run.profiles=dev" command will start a local tomcat server. Just run this command:

mvn spring-boot:run -Dspring-boot.run.profiles=dev

## Running the service locally with active profiles prod 

The "mvn spring-boot:run -Dspring-boot.run.profiles=prod" command will start a local tomcat server. Just run this command:

mvn spring-boot:run -Dspring-boot.run.profiles=prod

## JUnit test Code Coverage
Running the test using JUnit will automatically set in motion the JaCoCo agent. It will create a coverage report in binary format in the target directory, target/site/jacoco/index.html