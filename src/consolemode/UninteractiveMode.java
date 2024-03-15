package consolemode;

import citydata.types.City;
import workers.WeatherGetter;

import java.util.ArrayList;

import static workers.WeatherGetter.addWeatherRecordsToList;

public class UninteractiveMode {
    public static void runUninteractiveMode(String[] args){
        ArrayList<City> cities;
        ConsoleIO console = new ConsoleIO();
        cities = ProcessArgs.argsToCityList(args);
        WeatherGetter.addWeatherRecordsToList(cities);
        console.dumpAllRecords(cities);
    }
}
