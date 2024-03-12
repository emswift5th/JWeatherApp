package types;

public record CityWeather (
    String cityName,
    String country,
    double lon,
    double lat,
    int timezone,
    double sunrise,
    double sunset,
    String desc,
    float temp_k,
    float feels_like_k,
    float pressure_hpa,
    int humidity,
    int visibility_km,
    float wind_speed_kph,
    float wind_gust_kph,
    float wind_direction,
    int cloud_coverage,
    int rain_1hr_mm,
    int snow_1hr_mm,
    String api_name,
    String requested_date,
    String requested_time
) {}
