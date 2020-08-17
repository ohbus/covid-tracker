# COVID-19 Tracker

![Maven CI](https://github.com/ohbus/covid-tracker/workflows/Maven%20CI/badge.svg)

### Corona Virus Tracker Application

#### Application is live at [covid.subho.xyz](https://covid.subho.xyz) Data is updated at an interval of 30 minutes.

![Screenshot](https://github.com/ohbus/covid-tracker/blob/readme/assets/covid-tracker.gif)

#### This is a Spring Boot Application and integrated with Thymeleaf for data representation

#### Send in your Contribution using Pull Requests

### The executable runtime can be either downloaded from [packages](https://github.com/ohbus/covid-tracker/packages) or the [releases](https://github.com/ohbus/covid-tracker/releases) page

## To run this JAR (Requires JDK 11+)

_Note: You can always build your own JRE using `jlink` from JDK 11+_

#### **`java -jar covid-tracker-1.2.jar`**

##### Open **`localhost:8080`** from your browser to see the application running

## The Dockerized application instructions

### To run the application

#### **`docker run -d -p 80:8080 subhrodip/covid-tracker:1.2`**

### OR

#### **`docker run -d -p 80:8080 docker.pkg.github.com/ohbus/covid-tracker/application:1.2`**

##### Open **`localhost`** in your browser to see application running

