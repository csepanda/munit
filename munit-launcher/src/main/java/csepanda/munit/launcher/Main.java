package csepanda.munit.launcher;

public class Main {
    public static void main(String[] args) {
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
