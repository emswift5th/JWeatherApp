package APIs.open_weather_api_old;

public class Coord {
    float lon = 0;
    float lat = 0;

    public Coord(float lon, float lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Coord() {
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }
}
