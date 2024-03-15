package consolemode;

import citydata.types.City;
import citydata.types.CityWeather;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class ConsoleIO {

    public String consolePrompt(String line){
        println(line);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void println(String line){
        System.out.println(line);
    }

    //The following function is a really basic way of printing out a record.
    //This is not ideal and should be replaced with something better.
    public void dumpRecord(CityWeather weather){
        for (Field field : weather.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try{
                System.out.println(field.getName() + ": " + field.get(weather));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void dumpAllRecords(ArrayList<City> cities){
        for(City c : cities){
            for(CityWeather cw : c.weatherReadings){
                dumpRecord(cw);
                println("");
            }
        }
    }

    public ArrayList<String> parsedConsolePrompt(String prompt) {
        String in = consolePrompt(prompt);
        return getStringsFromArgs(in);
    }

    public ArrayList<String> getStringsFromArgs(String in) {
        String[] split = in.split(",");
        ArrayList<String> out = new ArrayList<>();
        for(String s : split){
            out.add(s.trim());
        }
        return out;
    }
}
