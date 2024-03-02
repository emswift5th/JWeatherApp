package jwnetwork;

import org.javatuples.Pair;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class Network {

    private final HttpClient client;

    public Network(HttpClient client) {
        //This lets us pass in a test object, or what ever
        this.client = client;
    }

    public Network() {
        //This has to be done in one line
        //For most usages we don't need to specify, so we assume that we create it like this
        this(HttpClient.newHttpClient());
    }

    public Pair<String, Integer> httpRequest (String URL) throws IOException, InterruptedException, UncheckedIOException {
        //Do better error handling here
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(URL)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return new Pair<>(response.body(), response.statusCode());
    }
}
