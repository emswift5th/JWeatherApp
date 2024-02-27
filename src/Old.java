import com.fasterxml.jackson.jr.ob.JSON;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Old {
    private static String getWeatherOpenWeather(String city) {
        String API_KEY = "58d62b8cfdb8f19a848f656572e9593c";
        HttpURLConnection httpconnection;
        String result = "";

        try {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY);
            httpconnection = (HttpURLConnection) url.openConnection();
            httpconnection.setRequestMethod("GET");
            int status = httpconnection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(httpconnection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            result = content.toString();
            httpconnection.disconnect();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }
    private static void parseJSON(String json_string) throws IOException {

        //Map<String,Object> OutputMap;
        //OutputMap = new HashMap<String, Object>(JSON.std.mapFrom(json_string));
        //String output = JSON.std.asString(json_string);
        Object ob = JSON.std.anyFrom(json_string);
        JSON.std.write(ob, new File("output.json"));
        String asdf = "";


        return;
    }
}
