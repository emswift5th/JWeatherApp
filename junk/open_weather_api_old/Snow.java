package APIs.open_weather_api_old;

import com.fasterxml.jackson.annotation.JsonProperty;

class Snow {
    @JsonProperty("1h")
    int one_hour_mm = 0;
    @JsonProperty("2h")
    int two_hour_mm = 0;

    public int getOne_hour_mm() {
        return one_hour_mm;
    }

    public void setOne_hour_mm(int one_hour_mm) {
        this.one_hour_mm = one_hour_mm;
    }

    public int getTwo_hour_mm() {
        return two_hour_mm;
    }

    public void setTwo_hour_mm(int two_hour_mm) {
        this.two_hour_mm = two_hour_mm;
    }
}
