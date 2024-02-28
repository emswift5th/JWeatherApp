package parseJSONAPI;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class OpenWeatherAPITest {

    private static String testJSONData;
    private static HashMap<String, String> testHashMap;

    //todo: redo all these tests...
    // oh dear


    @BeforeAll
    public static void loadTestJSONData() throws IOException {
        String filePath = "test/parseJSONAPI/OWTestInput.json";

        String JSONData;

        JSONData = new String(Files.readAllBytes(Paths.get(filePath)));

        testJSONData = JSONData;

    }

    @BeforeEach
    public void setupHashMapsAndParse(){
        OpenWeatherAPI JSONTest = new OpenWeatherAPI();
        testHashMap = JSONTest.parseJSON(testJSONData);
    }
    @Test
    void sampleJSONParseTest(){
        assertEquals(testHashMap.get("description"), "broken clouds");
        assertEquals(testHashMap.get("pressure"), "983.0");
        assertEquals(testHashMap.get("temperature"), "0.0");
    }
}