package consolemode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleIOTest {

    @Test
    void parsedConsolePrompt() {
        String input = "Bristol, Manchester,Leeds, Welwyn Garden City";
        ArrayList<String> output = new ArrayList<>(Arrays.asList("Bristol", "Manchester","Leeds", "Welwyn Garden City"));
        ConsoleIO console = new ConsoleIO();
        assertEquals(output,console.getStringsFromArgs(input));
    }
}