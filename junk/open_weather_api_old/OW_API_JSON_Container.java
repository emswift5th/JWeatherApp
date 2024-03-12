package APIs.open_weather_api_old;


import types.Weather_class;

public class OW_API_JSON_Container {
        Coord coord = new Coord();
        Weather[] weather = new Weather[0];
        String base = "";
        Main main = new Main();
        int visibility = 0;
        Wind wind = new Wind();
        Weather_class.Clouds clouds = new Weather_class.Clouds();
        long dt = 0;
        Rain rain = new Rain();
        Snow snow = new Snow();
        Sys sys = new Sys();
        int timezone = 0;
        long id = 0;
        String name = "";
        String cod = "";

        public OW_API_JSON_Container(Coord coord, Weather[] weather, String base, Main main, int visibility, Wind wind, Weather_class.Clouds clouds, long dt, Rain rain, Snow snow, Sys sys, int timezone, long id, String name, String cod) {
                this.coord = coord;
                this.weather = weather;
                this.base = base;
                this.main = main;
                this.visibility = visibility;
                this.wind = wind;
                this.clouds = clouds;
                this.dt = dt;
                this.rain = rain;
                this.snow = snow;
                this.sys = sys;
                this.timezone = timezone;
                this.id = id;
                this.name = name;
                this.cod = cod;
        }

        public OW_API_JSON_Container() {
        }

        public Coord getCoord() {
                return coord;
        }

        public void setCoord(Coord coord) {
                this.coord = coord;
        }

        public Weather[] getWeather() {
                return weather;
        }

        public void setWeather(Weather[] weather) {
                this.weather = weather;
        }

        public String getBase() {
                return base;
        }

        public void setBase(String base) {
                this.base = base;
        }

        public Main getMain() {
                return main;
        }

        public void setMain(Main main) {
                this.main = main;
        }

        public int getVisibility() {
                return visibility;
        }

        public void setVisibility(int visibility) {
                this.visibility = visibility;
        }

        public Wind getWind() {
                return wind;
        }

        public void setWind(Wind wind) {
                this.wind = wind;
        }

        public Weather_class.Clouds getClouds() {
                return clouds;
        }

        public void setClouds(Weather_class.Clouds clouds) {
                this.clouds = clouds;
        }

        public long getDt() {
                return dt;
        }

        public void setDt(long dt) {
                this.dt = dt;
        }

        public Rain getRain() {
                return rain;
        }

        public void setRain(Rain rain) {
                this.rain = rain;
        }

        public Snow getSnow() {
                return snow;
        }

        public void setSnow(Snow snow) {
                this.snow = snow;
        }

        public Sys getSys() {
                return sys;
        }

        public void setSys(Sys sys) {
                this.sys = sys;
        }

        public int getTimezone() {
                return timezone;
        }

        public void setTimezone(int timezone) {
                this.timezone = timezone;
        }

        public long getId() {
                return id;
        }

        public void setId(long id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCod() {
                return cod;
        }

        public void setCod(String cod) {
                this.cod = cod;
        }
}

