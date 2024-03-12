package APIs.open_weather_api_old;

//import org.json.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import types.CityWeather;
import types.Geo_class;
import types.Weather_class;
import jwnetwork.Network;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

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
        OW_API_JSON_Container APIData;
        //Creating and filling out a local weather record to store data from the API
        //Standardises weather data between APIs
        types.CityWeather cityWeather;
        Geo_class geo;
        Weather_class weather;
        ObjectMapper objectMapper = new ObjectMapper();

        APIData = objectMapper.readValue(JSONinput, OW_API_JSON_Container.class);

        geo = new Geo_class(
                APIData.getName(),
                APIData.getSys().getCountry(),
                //APIData.getCoord().getLon(),
                APIData.getCoord() != null ? APIData.getCoord().getLon() : 0.0f,
                APIData.getCoord() != null ? APIData.getCoord().getLat() : 0.0f,
                APIData.getTimezone(),
                APIData.getSys().getSunrise(),
                APIData.getSys().getSunset()
        );
        weather = new Weather_class(
                APIData.getWeather()[0].getDescription(),
                APIData.getMain().getTemp(),
                APIData.getMain().getFeels_like(),
                APIData.getMain().getPressure(),
                APIData.getMain().getHumidity(),
                APIData.getVisibility(),
                APIData.getWind().getSpeed(),
                APIData.getWind().getGust(),
                APIData.getWind().getSpeed(),
                APIData.getClouds().getAll(),
                APIData.getRain().getOne_hour_mm(),
                APIData.getSnow().getOne_hour_mm()
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
