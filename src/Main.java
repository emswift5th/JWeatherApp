import static consolemode.InteractiveMode.runInteractiveMode;
import static consolemode.UninteractiveMode.runUninteractiveMode;

public class Main {

    public static void main(String[] args) {
        //city = args.length > 0 ? args[0] : console.consolePrompt("Please enter a city:");
        if(args.length == 0){
            runInteractiveMode();
        }
        runUninteractiveMode(args);
    }
}