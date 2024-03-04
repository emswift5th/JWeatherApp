package types;

public record Geo(
        String cityName,
        String country,
        double lon,
        double lat,
        int timezone,
        double sunrise,
        double sunset
){
}


