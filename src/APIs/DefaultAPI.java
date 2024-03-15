package APIs;

import APIs.openweathermap.OWMAPI;
import citydata.types.City;
import citydata.types.CityWeather;

public class DefaultAPI {
    public static CityWeather getCityWeather(City c) {
        //todo make this actually find the default API
        //for now assuming that it's OpenWeatherMap because it's the only one that's implemented
        OWMAPI owAPI = new OWMAPI(System.getenv("OPEN_WEATHER_API_KEY"));
        return owAPI.getCityWeather(c);
    }
}
