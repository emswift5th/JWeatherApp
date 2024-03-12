package types;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestCities {
    Cities cities;
    @BeforeEach
    public void setupCities(){
        cities = new Cities();
        for(int i = 0; i < 5; i++){
            cities.addCity("City " + i);
        }
    }


    @Test
    public void listCities(){
        assertEquals(5, cities.list().size());
    }

    @Test
    public void addCity(){
        cities.addCity("City 5");
        assertEquals("City 5", cities.list().get("City 5").cityName);
    }

    @Test void getParticularCity(){

        assertEquals("City 3", cities.city("City 3").cityName);
    }

}
