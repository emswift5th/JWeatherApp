package APIs.openweathermap;

import citydata.types.City;
import citydata.types.CityWeather;
import jwnetwork.Network;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class testOWMAPI {

    private static CityWeather refWeather;
    private static String testJSONString;
    @BeforeAll
    static void initTestData(){
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
        testJSONString = "{\n" +
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
    void testAPIReturn() throws IOException, InterruptedException {
        CityWeather returnedWeather;
        City city = new City("City1");
        Network network = Mockito.mock(Network.class);
        when(network.httpRequest(
                "http://api.openweathermap.org/data/2.5/weather?q=" +
                    city.getCityName() +
                    "&appid=" +
                    System.getenv("OPEN_WEATHER_API_KEY"))).thenReturn(testJSONString);
        OWMAPI api = new OWMAPI(System.getenv("OPEN_WEATHER_API_KEY"));
        api.network = network;
        returnedWeather = api.getCityWeather(city);
        assertTrue(compareNoDateTime(refWeather, returnedWeather));

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
}
