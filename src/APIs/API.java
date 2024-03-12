package APIs;

import com.fasterxml.jackson.core.JsonProcessingException;
import types.CityWeather;

abstract public class API{
    protected String API_KEY;

    public abstract CityWeather getCityWeather(String city) throws JsonProcessingException;
    protected void setAPIKey(String key){
        API_KEY = key;
    }
    protected String getAPIKey()
    {
        return API_KEY;
    }
}
