# AirportManagement-APT


[![Build Status](https://travis-ci.com/AlessandroLemmo/AirportManagement-APT.svg?branch=master)](https://travis-ci.com/AlessandroLemmo/AirportManagement-APT)
[![Coverage Status](https://coveralls.io/repos/github/AlessandroLemmo/AirportManagement-APT/badge.svg?branch=master)](https://coveralls.io/github/AlessandroLemmo/AirportManagement-APT?branch=master)

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=alert_status)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)

[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=bugs)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=security_rating)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=sqale_index)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=code_smells)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=coverage)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)

[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com%3Aairportmanagement&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com%3Aairportmanagement)

The project simulates an airline management application in which planes and corresponding flights can be managed
To run the application and tests first of all make sure you have installed docker and maven on your computer.

### Run application
Execute the following steps.
1. Download project and open terminal in root folder of the project
2. Build the package file with the following command <br>
   - mvn -f airportmanagement/pom.xml -DskipTests=true package 
2. Start docker container: 
   - mvn -f airportmanagement/pom.xml docker:start
3. Start application: 
   - java -jar airportmanagement/target/airportmanagement-0.0.1-SNAPSHOT-jar-with-dependencies.jar 

### Run tests
Open terminal in the root folder of the project. <br>
To perform unit, integration, end-to-end tests run the following command: <br> 
- mvn -f airportmanagement/pom.xml clean verify 

If you want to also perform code coverage and mutation testing run the following command: <br>
- mvn -f airportmanagement/pom.xml clean verify -Pjacoco,mutation-testing <br>
To show the results of code coverage go to: airportmanagement/target/site/jacoco/site.html <br>
To show the results of mutation testing go to: airportmanagement/target/pit-reports -> open the last created folder corresponding to the last created report and click to index.html
