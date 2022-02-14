## Account Validator Service

POST - /v2/validator

URL             - /v2/validator
HTTP Operation  - POST
Functionality   - Account Validator is a spring boot rest service. It validates the account based on the account number.

## Request parameters

# First parameter
Name - account number 
Location             - Payload
Type                 - Long
Mandatory/Optioanl   - Mandatory

# Second parameter
Name                 -sources
Location             - Payload
Type                 - Array
Mandatory/Optioanl   - Optional

# Sample Response
{
    "result":[
        {
            "isValid": true,
            "source": "source1"
        },
        {
            "isValid": true,
            "source": "source2"
        }
    ]
}



## Setup and build
Building this project requires maven and JDK 1.8 and above installed. Default run on java11. For jdk8, we need to change the JDK version into pom.xml file.

## Run
Use the following maven command to run this project. 

### Getting Started
#  Get the source code
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

