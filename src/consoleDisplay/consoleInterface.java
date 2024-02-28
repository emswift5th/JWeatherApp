package consoleDisplay;

import java.util.HashMap;
import java.util.Scanner;

public class consoleInterface {
    public void printOutWeatherHashMap(HashMap<String, String> weatherData) {
        System.out.println(weatherData.get("description"));
        System.out.println(weatherData.get("temperature") + " Celsius");
        System.out.println(weatherData.get("pressure") + " hPa");
    }

    public String consolePrompt(String line){
        println(line);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void println(String line){
        System.out.println(line);
    }
}


