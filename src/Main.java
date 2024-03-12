import APIs.openweathermap.OpenWeatherMapAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import consoledisplay.ConsoleInterface;

import types.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        //Should not be in source code.
        //It's a free API so we can get away with it for now, but this really needs to be addressed.

        ConsoleInterface console = new ConsoleInterface();
        String city;

        //Process console params if they're there, else we ask the user
        if (args.length > 0) {
            city = args[0];

        } else {
            city = console.consolePrompt("Please enter a city:");
        }
        System.out.println(city + " it is! One sec...");

        //Create a new Open Weather Map API
        //initialise with the API key grabbed from environment variables
        OpenWeatherMapAPI owAPI = new OpenWeatherMapAPI(System.getenv("OPEN_WEATHER_API_KEY"));

        Cities cities = new Cities();
        cities.addCity(city);

        City curCity = cities.list().get(city);

        try {
            curCity.getReading(owAPI);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        //GUI stuff... for the future
        //POSTMAN, a way of calling APIs????

        //Print the weather at the specified location
        console.printRecord(curCity.getReading(0));

    }
}

