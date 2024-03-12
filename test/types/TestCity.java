package types;

import APIs.openweathermap.OpenWeatherMapAPI;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TestCity {
    @Test
    void testCityConstructor(){
        City city = new City("Bielefeld");
        assertEquals("Bielefeld", city.cityName);
    }

    @Test
    void testGetReadingWithAPI() throws JsonProcessingException {
        //should only return the last weatherReading
        OpenWeatherMapAPI wmAPI_1 = Mockito.mock(OpenWeatherMapAPI.class);
        OpenWeatherMapAPI wmAPI_2 = Mockito.mock(OpenWeatherMapAPI.class);
        CityWeather cityWeather1 = new CityWeather(
                "City1",
                "DE",
                0.2,
                0.3,
                2,
                12456,
                654321,
                "Cloudy with a chance of testing",
                2.0f,
                3.0f,
                3.3f,
                10,
                56,
                4.5f,
                8.9f,
                300,
                30,
                2,
                0,
                "Open Weather API",
                "1989-01-01",
                "02:32:45"
        );
        CityWeather cityWeather2 = new CityWeather(
                "City1",
                "DE",
                0.2,
                0.3,
                2,
                12456,
                654321,
                "Sunny all day",
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
                "Open Weather API",
                "1989-01-01",
                "02:32:45"
        );
        when(wmAPI_1.getCityWeather("City1")).thenReturn(cityWeather1);
        when(wmAPI_2.getCityWeather("City1")).thenReturn(cityWeather2);

        City city1 = new City("City1");
        city1.getReading(wmAPI_1);
        city1.getReading(wmAPI_2);

        assertEquals(city1.getReadings().get(1), cityWeather2);
    }
}