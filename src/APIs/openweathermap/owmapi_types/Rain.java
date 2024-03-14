package APIs.openweathermap.owmapi_types;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Rain(
        @JsonProperty("1h")
        int one_hour_mm,
        @JsonProperty("2h")
        int two_hour_mm) {
}
