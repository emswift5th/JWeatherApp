package unitconversions;

public class Convert {
    public float kelvinToCelsius(float kelvin) {
        return kelvin - 273.15f;
    }
    public float trimFloatTwoDecimals(float input) {
        return Float.parseFloat(String.format("%.2f", input));
    }

}
