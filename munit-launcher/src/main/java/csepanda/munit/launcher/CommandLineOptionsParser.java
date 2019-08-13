package csepanda.munit.launcher;

import com.beust.jcommander.JCommander;
import csepanda.munit.launcher.options.LauncherOptions;

public class CommandLineOptionsParser {
    public LauncherOptions parse(String[] argv) {
        var options = new LauncherOptions();

        var jc = JCommander.newBuilder()
            .addObject(options)
            .build();

        jc.setCaseSensitiveOptions(false);
        jc.parse(argv);

        return options;
    }
}
