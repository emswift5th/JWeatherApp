import network.Network;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        //Should not be in source code.
        String API_KEY = "58d62b8cfdb8f19a848f656572e9593c";

        String city = "NONE";

        //Process console params if they're there, else we ask the user
        if (args.length > 0) {
            city = args[0];

        } else {
            System.out.println("Enter a city: ");
            Scanner scanner = new Scanner(System.in);
            city = scanner.nextLine();

        }

        System.out.println(city + " it is! One sec...");

        //Get JSON data from OpenWeather
        //API key in URL not ideal
        String weatherJSON = null;
        try {
            Network httpOpenWeatherRequest = new Network();
            weatherJSON = httpOpenWeatherRequest.httpRequest("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY);
        } catch (IOException|InterruptedException e) {
            System.out.println("HTTP Request error!");
            throw new RuntimeException(e);
        }

        //GUI stuff... for the future
        //What should this return, what data should be made available?
        //POSTMAN, a way of calling APIs

        //Turn that data into something usable
        WeatherApp JSONParser = new WeatherApp();
        HashMap<String,String> WeatherData = new HashMap<>(JSONParser.parseJSON(weatherJSON));

        //Print the weather at the specified location
        System.out.println(WeatherData.get("description"));
        System.out.println(WeatherData.get("temperature") + " Celsius");
        System.out.println(WeatherData.get("pressure") + " hPa");

    }
}

