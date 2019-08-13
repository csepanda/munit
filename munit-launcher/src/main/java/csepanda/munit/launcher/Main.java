package csepanda.munit.launcher;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var cliParser = new CommandLineOptionsParser();
        var options = cliParser.parse(args);
        var optionsHandler = new Launcher();

        try {
            optionsHandler.handle(options);
        } catch (RuntimeException e) {
            System.exit(2);
        }
    }
}
