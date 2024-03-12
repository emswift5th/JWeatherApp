package APIs.openweathermap;

import APIs.API;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jwnetwork.Network;
import types.CityWeather;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.io.IOException;

public class OpenWeatherMapAPI extends API {

    public OpenWeatherMapAPI(String key){
        setAPIKey(key);
    }
    @Override
    public CityWeather getCityWeather(String city) throws JsonProcessingException {
        String weatherJSON;
        Network network = new Network();

        //grab JSON data from the web
        try {
            weatherJSON = network.httpRequest("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + getAPIKey());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Parse it into a local POJO representation, unique to OpenWeatherMap API
        OWMAPIData APIData;
        ObjectMapper objectMapper = new ObjectMapper();


        APIData = objectMapper.readValue(weatherJSON, OWMAPIData.class);


        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime curDateTime = LocalDateTime.now();

        //create a CityWeather object to return
        CityWeather cityWeather;
        cityWeather = new CityWeather(
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
                formatDate.format(curDateTime),
                formatTime.format(curDateTime)
        );

        return cityWeather;
    }
}
