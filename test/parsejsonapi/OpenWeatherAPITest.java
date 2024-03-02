package parsejsonapi;

import jwnetwork.Network;
import org.javatuples.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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

    private static HashMap<String, String> testData;
    private static String API_KEY;


    private static HashMap<String, String> parseJSONTester(String s){
        OpenWeatherAPI parserTest = new OpenWeatherAPI();
        return parserTest.parseJSON(testData.get(s));
    }

    @BeforeAll
    public static void loadTestDataStrings() throws IOException{
        API_KEY = System.getenv("OPEN_WEATHER_API_KEY");
        String filePath = "test/parsejsonapi/OWTestInput.csv";
        List<String> testDataLinesIn = Files.readAllLines(Paths.get(filePath));
        testData = new HashMap<>();
        for (String s : testDataLinesIn) {
            String[] data = s.split(",", 2);
            testData.put(data[0], data[1]);
        }
    }

    //parseJSON - Give it correct data, and verify
    @Test
    void parseJSON_GoodData(){
        //build reference hashmap and assert against the parser output
        HashMap<String, String> testHashMap = parseJSONTester("GOOD_DATA");
        HashMap<String, String> refHashMap = new HashMap<>();

        refHashMap.put("description", "broken clouds");
        refHashMap.put("pressure", "983.0");
        refHashMap.put("temperature", "0.0");

        assertEquals(refHashMap, testHashMap);
    }

    //parseJSON - Give it nonsense data, and verify
    @Test
    void parseJSON_BadData(){
        assertThrows(org.json.JSONException.class, ()->{HashMap<String, String> testHashMap = parseJSONTester("BAD_DATA");});
    }
    //parseJSON - Give it no data, and verify
    @Test
    void parseJSON_NoData(){
        assertThrows(org.json.JSONException.class, ()->{HashMap<String, String> testHashMap = parseJSONTester("BAD_DATA");});
    }

    //getWeatherAtCity - Give it a valid city
    @Test
    void getWeatherAtCity_GoodData() throws IOException, InterruptedException {
        Network testNetwork = mock(Network.class);

        HashMap<String, String> refHashMap = new HashMap<>();
        refHashMap.put("description", "broken clouds");
        refHashMap.put("pressure", "983.0");
        refHashMap.put("temperature", "0.0");

        when(testNetwork.httpRequest("http://api.openweathermap.org/data/2.5/weather?q="
                + testData.get("CITY")
                + "&appid=" + API_KEY)).thenReturn(new Pair<>(testData.get("GOOD_DATA"), 200));

        OpenWeatherAPI testAPI = new OpenWeatherAPI(testNetwork);
        testAPI.setAPI_KEY(API_KEY);

        HashMap<String, String> testHash = testAPI.getWeatherAtCity(testData.get("CITY"));
        assertEquals(refHashMap, testHash);
    }

    //getWeatherAtCity - Give it an invalid city
    @Test
    void getWeatherAtCity_InvalidCity(){
        OpenWeatherAPI testAPI = new OpenWeatherAPI();
        testAPI.setAPI_KEY(API_KEY);
        assertThrows(org.json.JSONException.class, ()->{
            testAPI.getWeatherAtCity(testData.get("INVALID_CITY"));
        });
    }

    //getWeatherAtCity - Give it an invalid API key
    @Test
    void getWeatherAtCity_invalidAPIKey(){
        OpenWeatherAPI testAPI = new OpenWeatherAPI();
        assertThrows(java.lang.RuntimeException.class, ()->{
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
