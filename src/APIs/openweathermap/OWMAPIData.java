package APIs.openweathermap;

import APIs.openweathermap.owmapi_types.*;

public record OWMAPIData(
        Coord coord,
        Weather[] weather,
        String base,
        Main main,
        int visibility,
        Wind wind,
        Clouds clouds,
        long dt,
        Rain rain,
        Snow snow,
        Sys sys,
        int timezone,
        long id,
        String name,
        String cod
) {
}
