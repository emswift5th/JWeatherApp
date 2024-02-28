package unitConversions;

import org.junit.jupiter.api.*;
import unitConversions.Convert;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class KelvinToCelsiusConverterTest {
    @Test
    public void kelvinToCelsiusTest () {
        Convert kelvinTest = new Convert();
        assertEquals(0.0f, kelvinTest.kelvinToCelsius(273.15f));
    }

    @Test
    public void kelvinToCelsiusTestFail () {
        Convert kelvinTest = new Convert();
        assertEquals(654, kelvinTest.kelvinToCelsius(273.15f));
    }
}
