package consolemode;

import consolemode.uninteractiveMode.argumentRecords.Arguments;
import consolemode.uninteractiveMode.argumentRecords.CityStateCountry;
import consolemode.uninteractiveMode.argumentRecords.CityWithCountry;
import config.LatLon;
import consolemode.uninteractiveMode.ProcessArgs;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static consolemode.uninteractiveMode.ProcessArgs.parseArgs;
import static org.junit.jupiter.api.Assertions.*;

class ConsoleIOTest {

    @Test
    void parsedConsolePromptCities() {
        String input = "-c Bristol; Manchester;Leeds; Welwyn Garden City";
        ArrayList<String> output = new ArrayList<>(Arrays.asList("Bristol", "Manchester","Leeds", "Welwyn Garden City"));

        ConsoleIO console = new ConsoleIO();
        assertEquals(output,console.getStringsFromArgs(input));
    }
    @Test
    void parsedConsolePromptCitiesAndCountryCode() {
        String input = "-cc Bristol,UK; Manchester,UK;Leeds,UK; Welwyn Garden City,UK;Bielefeld,Germany";
        ArrayList<String> output = new ArrayList<>(Arrays.asList("Bristol", "Manchester","Leeds", "Welwyn Garden City", "Bielefeld"));
        ConsoleIO console = new ConsoleIO();
        assertEquals(output,console.getStringsFromArgs(input));
    }

    @Test
    void argStringToHashmapTest(){
        String[] s = {"-c", "Bristol", "Trowbridge", "Tunbridge Wells",
        "-ll", "123456.1", "654321.9",
        "-api", "openweathermap"};
        HashMap<String, ArrayList<String>> reference = new HashMap<>();
        ArrayList<String> refStrings = new ArrayList<>();
        refStrings.add("Bristol");
        refStrings.add("Trowbridge");
        refStrings.add("Tunbridge Wells");
        reference.put("c", refStrings);
        refStrings = new ArrayList<>();
        refStrings.add("123456.1");
        refStrings.add("654321.9");
        reference.put("ll", refStrings);
        refStrings = new ArrayList<>();
        refStrings.add("openweathermap");
        reference.put("api", refStrings);

        assertEquals(reference, ProcessArgs.argStringToHashmap(s));

    }

    @Test
    void argParsingFail(){
        String[] s = {"Bristol, Trowbridge", "Tunbridge Wells", "-c", "Manchester"};
        assertThrows(RuntimeException.class, () -> ProcessArgs.argStringToHashmap(s));
    }

    @Test
    void parseCities(){
        String[] s = {"-c", "Bristol", "Trowbridge", "Tunbridge Wells", "Manchester"};
        ArrayList<String> refList = new ArrayList<>();
        refList.add("Bristol");
        refList.add("Trowbridge");
        refList.add("Tunbridge Wells");
        refList.add("Manchester");

        parseArgs(s);

        ArrayList<String> cities = Arguments.cities;

        assertEquals(refList, cities);
    }

    @Test
    void parseLatLon(){
        String[] s = {"-ll", "1.12345", "2.23456", "9.87654", "8.76543"};
        ArrayList<LatLon> refLatLons = new ArrayList<>();
        LatLon rec = new LatLon(1.12345f, 2.23456f);
        LatLon rec2 = new LatLon(9.87654f, 8.76543f);
        refLatLons.add(rec);
        refLatLons.add(rec2);
        parseArgs(s);

        assertEquals(refLatLons, Arguments.latLons);
    }

    @Test
    void failParseLatLon(){
        String[] s = {"-ll", "1.12345", "2.23456", "9.87654"};
        assertThrows(RuntimeException.class, () -> ProcessArgs.parseArgs(s));
    }

    @Test
    void parseCityStateCountry(){
        String[] s = {"-csc", "Swindon", "Wiltshire", "GB", "Bielefeld", "Westfalia", "DE"};
        ArrayList<CityStateCountry> refCSC = new ArrayList<>();
        refCSC.add(new CityStateCountry(
                "Swindon",
                "Wiltshire",
                "GB"
        ));
        refCSC.add(new CityStateCountry(
                "Bielefeld",
                "Westfalia",
                "DE"
        ));

        parseArgs(s);
        assertEquals(refCSC, Arguments.citiesStateCountry);
    }

    @Test
    void parseCityWithCountry(){
        String[] s = {"-cc", "Swindon", "GB", "Bielefeld", "DE"};
        ArrayList<CityWithCountry> refCWC = new ArrayList<>();
        refCWC.add(new CityWithCountry(
                "Swindon",
                "GB"
        ));
        refCWC.add(new CityWithCountry(
                "Bielefeld",
                "DE"
        ));

        parseArgs(s);
        assertEquals(refCWC, Arguments.citiesWithCountry);
    }
}