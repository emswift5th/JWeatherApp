package jwnetwork;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class NetworkTest {
    @Test
    void testNoURL(){
        //no URL means no string at the moment
        Network testNet = new Network();
        String noString = "";
        assertThrows(java.lang.IllegalArgumentException.class, ()->{testNet.httpRequest(noString);});
    }

    @Test
    void testGoodURL(){
        Network testNet = new Network();
        //set up the HttpClient

        String urlString = "http://google.com";
        assertDoesNotThrow(()->{testNet.httpRequest(urlString);});
    }

    //Can set up an HTTPClient as a mock object
    //Using Mockito!


}
