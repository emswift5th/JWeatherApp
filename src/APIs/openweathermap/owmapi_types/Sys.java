package APIs.openweathermap.owmapi_types;

public record Sys(
        String type,
        String id,
        String message,
        String country,
        double sunrise,
        double sunset
) {
}
