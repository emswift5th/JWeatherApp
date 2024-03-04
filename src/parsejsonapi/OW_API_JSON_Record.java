package parsejsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OW_API_JSON_Record(
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
){}

record Coord(
        float lon,
        float lat
){}
record Weather(
        int id,
        String main,
        String description,
        String icon
){}
record Main(
        float temp,
        float feels_like,
        float temp_min,
        float temp_max,
        float pressure,
        int humidity,
        long sea_level,
        long ground_level
){}
record Wind(
        float speed,
        int deg,
        int gust
){}

record Rain(
        @JsonProperty("1h")
        int one_hour_mm,
        @JsonProperty("2h")
        int two_hour_mm
){}

record Snow(
        @JsonProperty("1h")
        int one_hour_mm,
        @JsonProperty("2h")
        int two_hour_mm
){}

record Clouds(
        int all
){}

record Sys(
        String type,
        String id,
        String message,
        String country,
        double sunrise,
        double sunset
){}