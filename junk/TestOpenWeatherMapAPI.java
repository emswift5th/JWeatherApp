package APIs.openweathermap;

import jwnetwork.Network;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import citydata.types.City;
import citydata.types.CityWeather;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class TestOpenWeatherMapAPI {
    static CityWeather refWeather;
    static City testCity;
    static String JSONString;
    @BeforeAll
    public static void setupReference(){
        refWeather = new CityWeather(
                "City1",
                "DE",
                0.2f,
                0.3f,
                3600,
                1661834187,
                1661882248,
                "sunny all day",
                30.0f,
                28.0f,
                3300f,
                10,
                5,
                44.5f,
                88.9f,
                280,
                5,
                0,
                20,
                "Open Weather Map",
                1661860592,
                1661870592
        );

        CityWeather testCityWeather = refWeather;
        testCity = new City();
        testCity.addReading(refWeather);

        JSONString = "{\n" +
                "  \"coord\": {\n" +
                "    \"lon\": 0.2,\n" +
                "    \"lat\": 0.3\n" +
                "  },\n" +
                "  \"weather\": [\n" +
                "    {\n" +
                "      \"id\": 999,\n" +
                "      \"main\": \"Rain\",\n" +
                "      \"description\": \"sunny all day\",\n" +
                "      \"icon\": \"10d\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"base\": \"stations\",\n" +
                "  \"main\": {\n" +
                "    \"temp\": 30.0,\n" +
                "    \"feels_like\": 28.0,\n" +
                "    \"temp_min\": 297.56,\n" +
                "    \"temp_max\": 300.05,\n" +
                "    \"pressure\": 3300,\n" +
                "    \"humidity\": 10,\n" +
                "    \"sea_level\": 1015,\n" +
                "    \"grnd_level\": 933\n" +
                "  },\n" +
                "  \"visibility\": 5,\n" +
                "  \"wind\": {\n" +
                "    \"speed\": 44.5,\n" +
                "    \"deg\": 280,\n" +
                "    \"gust\": 88.9\n" +
                "  },\n" +
                "  \"rain\": {\n" +
                "    \"1h\": 0\n" +
                "  },\n" +
                "  \"snow\": {\n" +
                "    \"1h\": 20\n" +
                "  },\n" +
                "  \"clouds\": {\n" +
                "    \"all\": 5\n" +
                "  },\n" +
                "  \"dt\": 1661870592,\n" +
                "  \"sys\": {\n" +
                "    \"type\": 2,\n" +
                "    \"id\": 2075663,\n" +
                "    \"country\": \"DE\",\n" +
                "    \"sunrise\": 1661834187,\n" +
                "    \"sunset\": 1661882248\n" +
                "  },\n" +
                "  \"timezone\": 3600,\n" +
                "  \"id\": 3163858,\n" +
                "  \"name\": \"City1\",\n" +
                "  \"cod\": 200\n" +
                "}";
    }
    @Test
    public void getCityWeather() throws IOException, InterruptedException {
        //What if everything works as intended? This feels like over-testing.

        //Network network = Mockito.mock(Network.class);
        //when(network.httpRequest("http://api.openweathermap.org/data/2.5/weather?q=" + testCity.getName() + "&appid=" + System.getenv("OPEN_WEATHER_API_KEY"))).thenReturn(JSONString);

        //OpenWeatherMapAPI testAPI = new OpenWeatherMapAPI(System.getenv("OPEN_WEATHER_API_KEY"), network);
        OpenWeatherMapAPI testAPI = new OpenWeatherMapAPI(System.getenv("OPEN_WEATHER_API_KEY"));
        CityWeather returnWeather = testCity.getReading(testAPI);

        //Comparison must ignore date/time
        assertTrue(compareNoDateTime(returnWeather, refWeather));
    }
    @Test
    public void getCityWeather_mock() throws IOException, InterruptedException {
        //What if everything works as intended? This feels like over-testing.

        Network network = Mockito.mock(Network.class);
        when(network.httpRequest("http://api.openweathermap.org/data/2.5/weather?q=" + testCity.getName() + "&appid=" + System.getenv("OPEN_WEATHER_API_KEY"))).thenReturn(JSONString);

        OpenWeatherMapAPI testAPI = new OpenWeatherMapAPI(System.getenv("OPEN_WEATHER_API_KEY"), network);
        CityWeather returnWeather = testCity.getReading(testAPI);

        //Comparison must ignore date/time
        assertTrue(compareNoDateTime(returnWeather, refWeather));
    }
    private boolean compareNoDateTime(CityWeather c1, CityWeather c2){
        //Could I just use Reflection and iterate through this?
        return c1.cityName().equals(c2.cityName()) &&
                c1.country().equals(c2.country()) &&
                c1.lon() == c2.lon() &&
                c1.lat() == c2.lat() &&
                c1.timezone_offset_s() == c2.timezone_offset_s() &&
                c1.sunrise() == c2.sunrise() &&
                c1.desc().equals(c2.desc()) &&
                c1.temp_k() == c2.temp_k() &&
                c1.feels_like_k() == c2.feels_like_k() &&
                c1.pressure_hpa() == c2.pressure_hpa() &&
                c1.humidity() == c2.humidity() &&
                c1.visibility_m() == c2.visibility_m() &&
                c1.wind_speed_kph() == c2.wind_speed_kph() &&
                c1.wind_gust_kph() == c2.wind_gust_kph() &&
                c1.wind_direction() == c2.wind_direction() &&
                c1.cloud_coverage() == c2.cloud_coverage() &&
                c1.rain_1hr_mm() == c2.rain_1hr_mm() &&
                c1.snow_1hr_mm() == c2.snow_1hr_mm() &&
                c1.api_name().equals(c2.api_name());
    }
    @Test
    public void getCityWeatherNullCity(){
        //What if we gave it a null city?
    }
    @Test
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
