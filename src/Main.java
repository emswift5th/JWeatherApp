import com.fasterxml.jackson.core.JsonProcessingException;
import consoledisplay.ConsoleInterface;
import parsejsonapi.OpenWeatherAPI;

import java.util.HashMap;

import types.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        //Should not be in source code.
        //It's a free API so we can get away with it for now, but this really needs to be addressed.
        String API_KEY = System.getenv("OPEN_WEATHER_API_KEY");

        ConsoleInterface console = new ConsoleInterface();
        String city;

        //Process console params if they're there, else we ask the user
        if (args.length > 0) {
            city = args[0];

        } else {
            city = console.consolePrompt("Please enter a city:");
        }
        System.out.println(city + " it is! One sec...");

        //Create a new Open Weather Map API interface
        OpenWeatherAPI owAPI = new OpenWeatherAPI();

        //Set up API key and get the weather data hashmap
        owAPI.setAPI_KEY(API_KEY);
        CityWeather WeatherData = null;
        try {
            WeatherData = owAPI.getWeatherAtCity(city);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //GUI stuff... for the future
        //POSTMAN, a way of calling APIs????


        //Print the weather at the specified location

        //console.printOutWeatherHashMap(WeatherData);

    }
}

