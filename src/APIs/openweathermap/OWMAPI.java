package APIs.openweathermap;

import APIs.API;
import citydata.types.City;
import citydata.types.CityWeather;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jwnetwork.Network;

import java.io.IOException;

public class OWMAPI extends API {
    Network network;

    public OWMAPI(String key){
        setAPIKey(key);
    }

    public CityWeather getCityWeather (City city){
        if (network == null) {
            network = new Network();
        }
        if (API_KEY == null) {
            throw new RuntimeException("No API Key given, environment variable not set up?");
        }
        String weatherJSON = requestJSONDataForCity(city);
        OWMAPIData apiWeather = mapToObject(weatherJSON);
        return convertWeather(apiWeather);
    }

    private OWMAPIData mapToObject(String weatherJSON) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(weatherJSON, OWMAPIData.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String requestJSONDataForCity(City city){
        String weatherJSON;
        try{
            weatherJSON = network.httpRequest("http://api.openweathermap.org/data/2.5/weather?q=" + city.getCityName() + "&appid=" + getAPIKey());
        } catch (IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
        return weatherJSON;
    }

    private CityWeather convertWeather (OWMAPIData APIData){

        CityWeather internalWeather;

        internalWeather = new CityWeather(
                APIData.name(),
                APIData.sys() != null ? APIData.sys().country() : "",
                APIData.coord() != null ? APIData.coord().lon() : 0,
                APIData.coord() != null ? APIData.coord().lat() : 0,
                APIData.timezone(),
                APIData.sys() != null ? APIData.sys().sunrise() : 0,
                APIData.sys() != null ? APIData.sys().sunset() : 0,
                APIData.weather() != null ? APIData.weather()[0].description() : "",
                APIData.main() != null ? APIData.main().temp() : 0,
                APIData.main() != null ? APIData.main().feels_like() : 0,
                APIData.main() != null ? APIData.main().pressure() : 0,
                APIData.main() != null ? APIData.main().humidity() : 0,
                APIData.visibility(),
                APIData.wind() != null ? APIData.wind().speed() : 0,
                APIData.wind() != null ? APIData.wind().gust() : 0,
                APIData.wind() != null ? APIData.wind().deg() : 0,
                APIData.clouds() != null ? APIData.clouds().all() : 0,
                APIData.rain() != null ? APIData.rain().one_hour_mm() : 0,
                APIData.snow() != null ? APIData.snow().one_hour_mm() : 0,
                "Open Weather Map",
                System.currentTimeMillis()/1000,
                APIData.dt()
        );

        return internalWeather;
    }
}
