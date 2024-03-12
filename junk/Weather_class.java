package types;

public class Weather_class {
    String desc = "";
    float temp_k = 0;
    float feels_like_k = 0;
    float pressure_hpa = 0;
    int humidity = 0;
    int visibility_km = 0;
    float wind_speed_kph = 0;
    float wind_gust_kph = 0;
    float wind_direction = 0;
    int cloud_coverage;
    int rain_1hr_mm;
    int snow_1hr_mm;


    public Weather_class(String desc, float temp_k, float feels_like_k, float pressure_hpa, int humidity, int visibility_km, float wind_speed_kph, float wind_gust_kph, float wind_direction, int cloud_coverage, int rain_1hr_mm, int snow_1hr_mm) {
        this.desc = desc;
        this.temp_k = temp_k;
        this.feels_like_k = feels_like_k;
        this.pressure_hpa = pressure_hpa;
        this.humidity = humidity;
        this.visibility_km = visibility_km;
        this.wind_speed_kph = wind_speed_kph;
        this.wind_gust_kph = wind_gust_kph;
        this.wind_direction = wind_direction;
        this.cloud_coverage = cloud_coverage;
        this.rain_1hr_mm = rain_1hr_mm;
        this.snow_1hr_mm = snow_1hr_mm;
    }

    public Weather_class()
    {

    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public float getTemp_k() {
        return temp_k;
    }

    public void setTemp_k(float temp_k) {
        this.temp_k = temp_k;
    }

    public float getFeels_like_k() {
        return feels_like_k;
    }

    public void setFeels_like_k(float feels_like_k) {
        this.feels_like_k = feels_like_k;
    }

    public float getPressure_hpa() {
        return pressure_hpa;
    }

    public void setPressure_hpa(float pressure_hpa) {
        this.pressure_hpa = pressure_hpa;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getVisibility_km() {
        return visibility_km;
    }

    public void setVisibility_km(int visibility_km) {
        this.visibility_km = visibility_km;
    }

    public float getWind_speed_kph() {
        return wind_speed_kph;
    }

    public void setWind_speed_kph(float wind_speed_kph) {
        this.wind_speed_kph = wind_speed_kph;
    }

    public float getWind_gust_kph() {
        return wind_gust_kph;
    }

    public void setWind_gust_kph(float wind_gust_kph) {
        this.wind_gust_kph = wind_gust_kph;
    }

    public float getWind_direction() {
        return wind_direction;
    }

    public void setWind_direction(float wind_direction) {
        this.wind_direction = wind_direction;
    }

    public int getCloud_coverage() {
        return cloud_coverage;
    }

    public void setCloud_coverage(int cloud_coverage) {
        this.cloud_coverage = cloud_coverage;
    }

    public int getRain_1hr_mm() {
        return rain_1hr_mm;
    }

    public void setRain_1hr_mm(int rain_1hr_mm) {
        this.rain_1hr_mm = rain_1hr_mm;
    }

    public int getSnow_1hr_mm() {
        return snow_1hr_mm;
    }

    public void setSnow_1hr_mm(int snow_1hr_mm) {
        this.snow_1hr_mm = snow_1hr_mm;
    }

    public static class Clouds {
        int all = 0;

        public int getAll() {
            return all;
        }

        public void setAll(int all) {
            this.all = all;
        }
    }
}
