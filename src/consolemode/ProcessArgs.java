package consolemode;

import citydata.types.City;

import java.util.ArrayList;

public class ProcessArgs {
    public static ArrayList<City> argsToCityList(String[] args){
        ArrayList<City> cities = new ArrayList<>(args.length);
        for(String c : args){
            cities.add(new City(c));
        }
        return cities;
    }
    public static ArrayList<City> argsToCityList(String args){

        return new ArrayList<City>();
    }
}
