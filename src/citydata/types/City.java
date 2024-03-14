package citydata.types;

import java.util.ArrayList;

import APIs.API;
import com.fasterxml.jackson.core.JsonProcessingException;

public class City {
    public ArrayList<CityWeather> weatherReadings;
    String cityName = "";
    String country = "";
    float lon = 0;
    float lat = 0;
    int timezone = 0;
    long sunrise = 0;
    long sunset = 0;

    public City(){
        weatherReadings = new ArrayList<>();
    }
    public City(String name){
        weatherReadings = new ArrayList<>();
        cityName = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public long getSunrise() {
        return sunrise;
    }

    public void setSunrise(long sunrise) {
        this.sunrise = sunrise;
    }

    public long getSunset() {
        return sunset;
    }

    public void setSunset(long sunset) {
        this.sunset = sunset;
    }
}
