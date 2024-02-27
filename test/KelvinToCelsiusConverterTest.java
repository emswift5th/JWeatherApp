import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class KelvinToCelsiusConverterTest {
    @Test
    public void kelvinToCelsiusTest () {
        WeatherApp kelvinTest = new WeatherApp();
        assertEquals(0.0f, kelvinTest.kelvinToCelsius(273.15f));
    }

    @Test
    public void kelvinToCelsiusTestFail () {
        WeatherApp kelvinTest = new WeatherApp();
        assertEquals(654, kelvinTest.kelvinToCelsius(273.15f));
    }
}
