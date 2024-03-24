package consolemode.uninteractiveMode;

import consolemode.ConsoleIO;
import workers.WeatherGetter;

import static consolemode.uninteractiveMode.ProcessArgs.parseArgs;
import static workers.WeatherGetter.addWeatherRecordsToList;

public class UninteractiveMode {

    public static void runUninteractiveMode(String[] args){
        ConsoleIO console = new ConsoleIO();

        //parse command line arguments
        parseArgs(args);
        //go through Arguments and get LatLon data for each city, so as
        //to be unambiguous
        //argsToCities(args);

        //lookup weather and update local records
        //print records

        //WeatherGetter.addWeatherRecordsToList(cities);
        //console.dumpAllRecords(cities);
    }
}
