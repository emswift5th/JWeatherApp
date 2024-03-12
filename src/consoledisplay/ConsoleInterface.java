package consoledisplay;

import types.CityWeather;

import java.lang.reflect.Field;
import java.util.Scanner;

public class ConsoleInterface {

    public String consolePrompt(String line){
        println(line);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void println(String line){
        System.out.println(line);
    }

    public void printRecord(CityWeather weather){
        for (Field field : weather.getClass().getDeclaredFields()){
            field.setAccessible(true);
            try{
                System.out.println(field.getName() + ": " + field.get(weather));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
