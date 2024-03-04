package parsejsonapi;

//import org.json.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import types.CityWeather;
import types.Geo;
import types.Weather;
import unitconversions.Convert;
import jwnetwork.Network;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
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

    public CityWeather parseJSON(String JSONinput) throws JsonProcessingException {
        //Parsing JSON
        OW_API_JSON_Record APIData;
        //Creating and filling out a local weather record to store data from the API
        //Standardises weather data between APIs
        types.CityWeather cityWeather;
        types.Geo geo;
        types.Weather weather;
        ObjectMapper objectMapper = new ObjectMapper();

        APIData = objectMapper.readValue(JSONinput, OW_API_JSON_Record.class);
        geo = new Geo(
                APIData.name(),
                APIData.sys().country(),
                APIData.coord().lon(),
                APIData.coord().lat(),
                APIData.timezone(),
                APIData.sys().sunrise(),
                APIData.sys().sunset()
        );
        weather = new Weather(
                APIData.weather()[0].description(),
                APIData.main().temp(),
                APIData.main().feels_like(),
                APIData.main().pressure(),
                APIData.main().humidity(),
                APIData.visibility(),
                APIData.wind().speed(),
                APIData.wind().gust(),
                APIData.wind().speed(),
                APIData.clouds().all(),
                APIData.rain().one_hour_mm(),
                APIData.snow().one_hour_mm()
        );
        cityWeather = new CityWeather(geo, weather);
        //Jackson, via DTO?

        //Putting data into hashmap
        //Shift-F6 to rename a variable
        return cityWeather;
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

    public CityWeather getWeatherAtCity(String city) throws JsonProcessingException {
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
