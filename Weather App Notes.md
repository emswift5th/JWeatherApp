# Weather App Project 1.0

Download weather data for a specified city from OpenWeather and display it in the console.

## Need to know

Java input and output

How to use the OpenWeather API

Manipulating JSON data - returned by the OpenWeather API

# Version 2.0 stuff?

GUI...

Postman?

Remove API key from source & URL if possible

Classes should do one and only one thing, try breaking down Main. Breaking up WeatherApp.java

Split off JSON/Jackson & jwnetwork into directory - package

Jackson via DTO

Static etc

Automated testing for every piece possible

Gradle

Test Driven Development

notes from Mistress

* Refactoring the app into seperate, testable classes
* Using TDD to test those classes and make sure they do what you expect
* If you can, try and test the API as well - can you get it to return a 401? A 500? A 200? 
* display better error results - if the API gives an error message, print that to the console
* Change your app to return results from multiple cities at once
* Change your app to call multiple weather APIs and give the average of the results
* 
