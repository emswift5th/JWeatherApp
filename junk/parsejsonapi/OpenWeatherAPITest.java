package parsejsonapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import jwnetwork.Network;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import types.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class OpenWeatherAPITest {
    /* Test the following methods:
        openWeatherAPI.parseJSON()
        openWeatherAPI.getWeatherAtCity()
     */

    //testData is read in from CSV
    private static HashMap<String, String> testData;
    //used to create good data to test against
    private static TestCityWeather refWeather;
    private static String API_KEY;


    private static TestCityWeather parseJSONTester(String s) throws JsonProcessingException {
        OpenWeatherAPI parserTest = new OpenWeatherAPI();
        return parserTest.parseJSON(testData.get(s));
    }

    @BeforeAll
    public static void initTestData() throws IOException{
        API_KEY = System.getenv("OPEN_WEATHER_API_KEY");
        String filePath = "test/parsejsonapi/OWTestInput.csv";
        List<String> testDataLinesIn = Files.readAllLines(Paths.get(filePath));
        testData = new HashMap<>();
        for (String s : testDataLinesIn) {
            String[] data = s.split(",", 2);
            testData.put(data[0], data[1]);
        }

        //create a reference CityWeather object

        Geo_class geo;
        Weather_class weather;

        geo = new Geo_class(
                "Manchester",
                "GB",
                -2.2374,
                53.4809,
                0,
                1708672323,
                1708709586
        );
        weather = new Weather_class(
                "broken clouds",
                (float)273.15,
                (float)277.61,
                983,
                79,
                10000,
                (float)4.12,
                0,
                210,
                75,
                0,
                0
        );
        refWeather = new TestCityWeather(geo, weather);

    }

    //parseJSON - Give it correct data, and verify
    @Test
    void parseJSON_GoodData() throws JsonProcessingException {
        //build reference hashmap and assert against the parser output
        TestCityWeather testWeather = parseJSONTester("GOOD_DATA");

        Assertions.assertEquals(refWeather, testWeather);
    }

    //parseJSON - Give it nonsense data, and verify
    @Test
    void parseJSON_BadData(){
        Assertions.assertThrows(org.json.JSONException.class, ()->{
            TestCityWeather testWeather = parseJSONTester("BAD_DATA");});
    }
    //parseJSON - Give it no data, and verify
    @Test
    void parseJSON_NoData(){
        Assertions.assertThrows(org.json.JSONException.class, ()->{
            TestCityWeather testWeather = parseJSONTester("BAD_DATA");});
    }

    //getWeatherAtCity - Give it a valid city
    @Test
    void getWeatherAtCity_GoodData() throws IOException, InterruptedException {
        Network testNetwork = Mockito.mock(Network.class);

        Mockito.when(testNetwork.httpRequest("http://api.openweathermap.org/data/2.5/weather?q="
                + testData.get("CITY")
                + "&appid=" + API_KEY)).thenReturn(testData.get("GOOD_DATA"));

        OpenWeatherAPI testAPI = new OpenWeatherAPI(testNetwork);
        testAPI.setAPI_KEY(API_KEY);

        TestCityWeather testWeather = testAPI.getWeatherAtCity(testData.get("CITY"));
        Assertions.assertEquals(refWeather, testWeather);
    }

    //getWeatherAtCity - Give it an invalid city
    @Test
    void getWeatherAtCity_InvalidCity(){
        OpenWeatherAPI testAPI = new OpenWeatherAPI();
        testAPI.setAPI_KEY(API_KEY);
        Assertions.assertThrows(org.json.JSONException.class, ()->{
            testAPI.getWeatherAtCity(testData.get("INVALID_CITY"));
        });
    }

    //getWeatherAtCity - Give it an invalid API key
    @Test
    void getWeatherAtCity_invalidAPIKey(){
        OpenWeatherAPI testAPI = new OpenWeatherAPI();
        Assertions.assertThrows(java.lang.RuntimeException.class, ()->{
           testAPI.getWeatherAtCity(testData.get(testData.get("CITY")));
        });
    }



}

/*
class OpenWeatherAPITest {


    private static List<String> testJSONData;
    private static HashMap<String, HashMap<String, String>> testHashMaps;

    static {
        testHashMaps = new HashMap<>();
    }
    private static final List<String> stringTypes = Arrays.asList(
            "GOOD_DATA",
            "BAD_DATA",
            "NO_DATA",
            "DEBUG_END");

    @BeforeAll
    public static void loadTestJSONData() throws IOException {
        String filePath = "test/parseJSONAPI/OWTestInput.csv";
        testJSONData = Files.readAllLines(Paths.get(filePath));


    }


    @BeforeEach
    public void setupHashMapsAndParse(){
        OpenWeatherAPI JSONTest = new OpenWeatherAPI();

        for (int i=0; i<stringTypes.size(); i++) {
            OpenWeatherAPITest.testHashMaps.put(stringTypes.get(i), JSONTest.parseJSON(testJSONData.get(i)));
        }

    }
    @Test
    void sampleJSONParseTest(){


    }
}*/
