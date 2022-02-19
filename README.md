# WeatherApp Craft Demo

## Requirements :

Build a Working prototype of API layer that will be used in hypothetical Weather App.

Build API's around temperature data provided by weather sensors to provide below data to users around the globe
* Provide average temperature details for given time( Day, month and hour) and location(latitude/longitude).
* Provide highest and lowest temperature across the globe for a time range.
* Supprt further insights of weather data using the API layer.


## Solution Overview:

API's using weather data(temparature) will consume the data that has been precomputed based on input from sensors. Precomputed details are queried based on the location and time.
Due to pre-compute process, API's are really lightweight and enables high QPS requirement. Raw data will be available for any adhoc query needs too on seperate tables.

API are built based on REST style.


## Database design:


## Technology Stack:
* SpringBoot
* Java 11
* MySQL (Azure Service)
* Actuator - App Health , environment details
* Swagger - API Contract details
* Maven
* Docker (opnjdk alpine image)


## API Contracts


## Features currently supported
* List locations that are currently monitored.
* Get Average tempature of a location based on lat/long and time(day,month and hour)
* Get Global temperature for a time range provided.



## Steps to exeucte the application
* Clone the repo.
* Run locally using either
  * Maven wrapper - ./mvnw springboot:run 
  * Using Java executbale - java -jar target/Weather-0.0.1-SNAPSHOT.jar
  * Run as docker container by
      * Generate Docker Image based on Docker file by executing  docker build -t <name:tag> . 
      * Create a new docker container by executing docker run -p 8080:8080 --name weather demo/weather. (App by default runs on 8080)
      









