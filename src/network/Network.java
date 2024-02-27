package network;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Network {
    public String httpRequest (String URL) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient(); //what does autocloseable mean?
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        //System.out.println(response.statusCode());
        //System.out.println(response.body());

        return response.body();
    }
}
