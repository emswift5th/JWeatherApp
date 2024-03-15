package workers;

import APIs.API;
import APIs.DefaultAPI;
import citydata.types.City;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;

public class WeatherGetter {
    public static ArrayList<City> addWeatherRecordsToList(ArrayList<City> cities){
        for (City c : cities){
            c = addWeatherRecord(c);
        }
        return cities;
    }
    public static ArrayList<City> addWeatherRecordsToList(ArrayList<City> cities, API api) throws JsonProcessingException {
        for (City c : cities) {
            c = addWeatherRecord(c, api);
        }
        return cities;
    }
    public static City addWeatherRecord(City city, API api) throws JsonProcessingException {
        city.weatherReadings.add(api.getCityWeather(city));
        return city;
    }
    public static City addWeatherRecord(City city)
    {
        city.weatherReadings.add(DefaultAPI.getCityWeather(city));
        return city;
    }
}
