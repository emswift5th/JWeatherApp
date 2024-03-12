package types;

import java.lang.reflect.Array;
import java.util.ArrayList;

import APIs.API;
import com.fasterxml.jackson.core.JsonProcessingException;

public class City {
    private ArrayList<CityWeather> weatherReadings;
    String cityName = "";
    String country = "";
    double lon = 0;
    double lat = 0;
    int timezone = 0;
    double sunrise = 0;
    double sunset = 0;

    public City(){
        weatherReadings = new ArrayList<>();
    }
    public City(String name){
        weatherReadings = new ArrayList<>();
        cityName = name;
    }

    public CityWeather getReading(int reading)
    {
        //return one single weather reading, of the specified index
        return weatherReadings.get(reading);
    }
    public CityWeather getReading(API api) throws JsonProcessingException {
        weatherReadings.add(api.getCityWeather(cityName));
        return weatherReadings.get(weatherReadings.size()-1);

    }
    public ArrayList<CityWeather> getReadings(){
        return weatherReadings;
    }

}
