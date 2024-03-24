package consolemode.uninteractiveMode;

import citydata.types.City;

import java.util.ArrayList;
import java.util.HashMap;

import config.*;
import consolemode.uninteractiveMode.argumentRecords.Arguments;
import consolemode.uninteractiveMode.argumentRecords.CityStateCountry;
import consolemode.uninteractiveMode.argumentRecords.CityWithCountry;

public class ProcessArgs {

    public static void parseArgs(String[] args){
        //Create Args Hashmap
        //c - city1, city2, city3
        //cc - city1, gb, city2, de etc
        HashMap<String, ArrayList<String>> parsedArgs = argStringToHashmap(args);

        //Insert them into config
        for(String s : parsedArgs.keySet()){
            switch(s){
                case "c":
                    Arguments.cities = parsedArgs.get(s);
                    break;
                case "cc":
                    parseCityCountry(parsedArgs.get(s));
                    break;
                case "csc":
                    parseCityStateCountry(parsedArgs.get(s));
                    break;
                case "ll":
                    parseLatLon(parsedArgs.get(s));
                    break;
                case "api":
                    Arguments.APIs = parsedArgs.get(s);
                    break;
            }
        }
    }

    public static void parseLatLon(ArrayList<String> strings) {
        if((strings.size() % 2) != 0)
        {
            throw new RuntimeException("Invalid lat/long pairs, " +
                    "number of parameters not divisible by two.\n" +
                    "eg 12345.6, -98765.4");
        }
        for(int i = 0; i < strings.size(); i = i+2){
            LatLon record = new LatLon(Float.parseFloat(strings.get(i)), Float.parseFloat(strings.get(i+1)));
            Arguments.latLons.add(record);
        }
    }

    public static void parseCityStateCountry(ArrayList<String> strings) {
        if((strings.size() % 3) != 0)
        {
            throw new RuntimeException("Invalid City State Country triplets.\n" +
                    "Please supply City State Country in triplets, separated by spaces.\n" +
                    "eg Swindon Wiltshire GB");
        }
        for(int i = 0; i < strings.size(); i = i+3){
            CityStateCountry csc = new CityStateCountry(
                    strings.get(i), strings.get(i+1), strings.get(i+2)
            );
            Arguments.citiesStateCountry.add(csc);
        }
    }

    public static void parseCityCountry(ArrayList<String> strings) {
        if((strings.size() % 2) != 0)
        {
            throw new RuntimeException("Invalid City Country pairs.\n" +
                    "Please supply City and Country in pairs, separated by spaces.\n" +
                    "eg Swindon GB");
        }
        for(int i = 0; i < strings.size(); i = i + 2){
            CityWithCountry cwc = new CityWithCountry(
                    strings.get(i), strings.get(i+1)
            );
            Arguments.citiesWithCountry.add(cwc);
        }
    }

    public static HashMap<String, ArrayList<String>> argStringToHashmap(String[] args) {
        HashMap<String,ArrayList<String>> parsedArgs = new HashMap<>();
        ArrayList<String> tempArrayList = new ArrayList<>();
        String argType = null;

        for(String s : args){
            //does this start with a -? if so it's an option

            if (s.charAt(0) == '-'
                    && s.length() > 1
                    && Character.isLetter(s.charAt(1))){
                //then we know this is an option specifier,
                //and we need to start a new list of arguments to go with it
                argType = s.substring(1);
                tempArrayList = new ArrayList<>();
                parsedArgs.put(argType,tempArrayList);
            } else {
                if (argType == null) {
                    //we were given a parameter without an argument type, this is invalid
                    throw new RuntimeException("Invalid arguments.");
                }
                else{
                    //we've been given an argument type, so this must be a parameter
                    parsedArgs.get(argType).add(s);
                }
            }
        }
        return parsedArgs;
    }


    public static ArrayList<City> argsToCities(Arguments args){
        ArrayList<City> cities = new ArrayList<>();
        //go through all of the arguments and fill out a list of cities

        cities.addAll(argCitiesToCitiesList(args.cities));
        cities.add(argCitiesWithCountryToCitiesList(args.citiesWithCountry));
        cities.add(argCitiesWithStateCountryToCitiesList(args.citiesStateCountry));
        cities.add(argLatLonsToCitiesList(args.latLons));

        return cities;
    }

    private static City argLatLonsToCitiesList(ArrayList<LatLon> latLons) {
        return null;
    }

    private static City argCitiesWithStateCountryToCitiesList(ArrayList<CityStateCountry> citiesStateCountry) {
        return null;

    }

    private static City argCitiesWithCountryToCitiesList(ArrayList<CityWithCountry> citiesWithCountry) {
        return null;

    }

    private static ArrayList<City> argCitiesToCitiesList(ArrayList<String> argCities) {
        ArrayList<City> cities = new ArrayList<>();
        for(String s : argCities){
            cities.add(new City(s));
        }
        return cities;
    }


}
