package APIs.openweathermap.owmapi_types;

public record Main(
        float temp,
        float feels_like,
        float temp_min,
        float temp_max,
        float pressure,
        int humidity,
        long sea_level,
        long ground_level
) {
}
