package parsejsonapi;

import org.json.*;
import unitconversions.Convert;
import jwnetwork.Network;

import java.io.IOException;
import java.net.http.HttpClient;
import java.util.HashMap;

public class OpenWeatherAPI {

    private String API_KEY;
    private final Network httpRequest;

    public OpenWeatherAPI() {
        this.httpRequest = new Network();
    }

    public OpenWeatherAPI(Network httpRequest) {
        this.httpRequest = httpRequest;
    }

    public String getAPI_KEY() {
        return API_KEY;
    }

    public void setAPI_KEY(String API_KEY) {
        this.API_KEY = API_KEY;
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
        Convert converter = new Convert();

        tempCelsius = converter.kelvinToCelsius(mainData.getFloat("temp"));
        tempCelsius = converter.trimFloatTwoDecimals(tempCelsius);

        weatherDataHashmap.put("description", weatherDataDescription.getString("description"));
        weatherDataHashmap.put("temperature", Float.toString(tempCelsius));
        weatherDataHashmap.put("pressure", Float.toString(mainData.getFloat("pressure")));

        return weatherDataHashmap;
    }


    private String getJSONData(String city) {
        String weatherJSON;
        //Connect to the API and pull data
        try {
            weatherJSON = httpRequest.httpRequest("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY).getValue0();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        return weatherJSON;
    }

    public HashMap<String, String> getWeatherAtCity(String city){
        //Take a city name string, return a hashmap of the weather data at this city.

        if (API_KEY == null) {
            //I don't know enough about this to make this useful
            throw new RuntimeException("No API key given!");
        }

        //Download JSON data from OpenWeather API
        String weatherJSON = getJSONData(city);

        //Parse and return it
        return parseJSON(weatherJSON);
    }

}
