package APIs.openweathermap.owmapi_types;

public record Weather(
        int id,
        String main,
        String description,
        String icon
){}
