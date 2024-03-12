package types;

import java.util.HashMap;

public class Cities {
    private final HashMap<String, City> Cities;

    public Cities(){
        Cities = new HashMap<>();
    }

    public void addCity(String cityName){
        //see if this city is in the list, if not, add a new one
        if(!Cities.containsKey(cityName)) {
            City city = new City(cityName);
            Cities.put(cityName, city);
        }
    }
    public HashMap<String, City> list(){
        return Cities;
    }
    public City city(String cityName){
        return Cities.get(cityName);
    }
}
