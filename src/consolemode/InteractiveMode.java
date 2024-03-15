package consolemode;

import citydata.types.City;
import java.util.ArrayList;
import static workers.WeatherGetter.addWeatherRecordsToList;

public class InteractiveMode {
    public static void runInteractiveMode(){
        ArrayList<City> cities = new ArrayList<>();
        ConsoleIO console = new ConsoleIO();
        int max_iter = 99;
        int i = 0;
        while(i < max_iter)
        {
            ArrayList<String> input = console.parsedConsolePrompt("Please enter a list of cities, separated by commas.\n" +
                    "or enter !quit to end program.");
            if (doQuit(input, console)) return;
            //our input list should now work as a list of cities,
            //so let's initialise our cities list.
            for(String s: input){
                cities.add(new City(s));
            }
            //get weather readings for all given cities
            addWeatherRecordsToList(cities);
            //unceremoniously dump records to console
            console.dumpAllRecords(cities);
            i++;
        }
        throw new RuntimeException("Maximum iterations reached! Pulling the emergency brake...");
    }

    private static boolean doQuit(ArrayList<String> input, ConsoleIO console) {
        if(input.get(0).equals("!quit")){
            console.println("Goodbye!");
            return true;
        }
        return false;
    }
}
