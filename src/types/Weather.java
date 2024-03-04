package types;

public record Weather (
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
    int snow_1hr_mm
){}
