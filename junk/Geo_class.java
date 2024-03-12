package types;

public class Geo_class {
        String cityName = "??";
        String country = "??";
        double lon = 0;
        double lat = 0;
        int timezone = 0;
        double sunrise = 0;
        double sunset = 0;

    public Geo_class(String cityName, String country, double lon, double lat, int timezone, double sunrise, double sunset) {
        this.cityName = cityName;
        this.country = country;
        this.lon = lon;
        this.lat = lat;
        this.timezone = timezone;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public Geo_class(){

    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public double getSunrise() {
        return sunrise;
    }

    public void setSunrise(double sunrise) {
        this.sunrise = sunrise;
    }

    public double getSunset() {
        return sunset;
    }

    public void setSunset(double sunset) {
        this.sunset = sunset;
    }
}


