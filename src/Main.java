import APIs.openweathermap.OWMAPI;
import citydata.types.City;
import citydata.types.CityWeather;
import consoledisplay.ConsoleInterface;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String city;
        ConsoleInterface console = new ConsoleInterface();

        //Process console params if they're there, else we ask the user
        city = args.length > 0 ? args[0] : console.consolePrompt("Please enter a city:");
        System.out.println(city + ", it is! One sec...");

        //Current version of application only supports one API, so...
        OWMAPI owAPI = new OWMAPI(System.getenv("OPEN_WEATHER_API_KEY"));

        //Current version of application only supports one city, but doing this makes me feel better
        ArrayList<City> cities = new ArrayList<>();
        City curCity = new City(city);
        cities.add(curCity);
        //Since there's only one
        CityWeather APIReturn = owAPI.getCityWeather(cities.get(0));
        cities.get(0).weatherReadings.add(APIReturn);

        //Print the weather at the specified location
        console.printRecord(cities.get(0).weatherReadings.get(0));
    }
}