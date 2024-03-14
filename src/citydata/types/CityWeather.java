package citydata.types;

public record CityWeather (
    String cityName,
    String country,
    float lon,
    float lat,
    int timezone_offset_s,
    long sunrise,
    long sunset,
    String desc,
    float temp_k,
    float feels_like_k,
    float pressure_hpa,
    int humidity,
    int visibility_m,
    float wind_speed_kph,
    float wind_gust_kph,
    float wind_direction,
    int cloud_coverage,
    int rain_1hr_mm,
    int snow_1hr_mm,
    String api_name,
    long requestTime_s,
    long readingTime_s
) {}
