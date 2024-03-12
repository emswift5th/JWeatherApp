package APIs.openweathermap;

import org.junit.jupiter.api.Test;

public class TestOpenWeatherMapAPI {

    @Test
    public void getCityWeather(){
        //What if everything works as intended?
    }
    @Test
    public void getCityWeatherNullCity(){
        //What if we gave it a null city?
    }
    public void getCityWeatherBadCity(){
        //What if we gave it a null city?
        //Would need to hit the API to test this...
    }
    @Test
    public void getCityWeatherBadHTTP(){
        //What if the httpRequest went wrong?
    }
    @Test
    public void getCityWeatherEmptyReturn(){
        //What if the API returned nothing?

    }

}
