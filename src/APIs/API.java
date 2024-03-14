package APIs;

import citydata.types.City;
import com.fasterxml.jackson.core.JsonProcessingException;
import citydata.types.CityWeather;

abstract public class API{
    protected String API_KEY;

    public abstract CityWeather getCityWeather(City city) throws JsonProcessingException;
    protected void setAPIKey(String key){
        API_KEY = key;
    }
    protected String getAPIKey()
    {
        return API_KEY;
    }
}
