import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.*;
import java.util.HashMap;

public class WeatherApp {

    public float kelvinToCelsius(float kelvin) {
        return kelvin - 273.15f;
    }

    public float trimFloatTwoDecimals(float input) {
        return Float.parseFloat(String.format("%.2f", input));
    }

    public HashMap<String, String> parseJSON(String JSONinput) {
        //Making hashmap to store weather data
        HashMap<String, String> weatherDataHashmap = new HashMap<String, String>();
        //Parsing JSON
        JSONObject obj = new JSONObject(JSONinput);
        JSONObject weatherDataDescription = new JSONObject(obj.getJSONArray("weather").get(0).toString());
        JSONObject mainData = new JSONObject(obj.getJSONObject("main").toString());

        //Jackson, via DTO?

        //Putting data into hashmap
        //Shift-F6 to rename a variable
        float tempCelsius;

        tempCelsius = kelvinToCelsius(mainData.getFloat("temp"));
        tempCelsius = trimFloatTwoDecimals(tempCelsius);

        weatherDataHashmap.put("description", weatherDataDescription.getString("description"));
        weatherDataHashmap.put("temperature", Float.toString(tempCelsius));
        weatherDataHashmap.put("pressure", Float.toString(mainData.getFloat("pressure")));

        return weatherDataHashmap;
    }

}
