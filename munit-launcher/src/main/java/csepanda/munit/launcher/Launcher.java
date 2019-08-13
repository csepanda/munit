package csepanda.munit.launcher;

import com.beust.jcommander.JCommander;
import csepanda.munit.launcher.options.LauncherOptions;

public class Launcher {
    private LauncherServicesProducer servicesProducer = new LauncherServicesProducer();

    public void handle(LauncherOptions options) {
        if (options.isHelp()) {
            var jc = JCommander.newBuilder().addObject(options).build();

            jc.usage();
            return;
        }

        var classes = loadClasses(options);
        var launcher = servicesProducer.launcher(options);

        try {
            var results = launcher.launch(classes);

            for (var result : results) {
                System.out.println(result.getTest().getName() + " " + result.getStatus());
            }
        } catch (Exception e) {
            handleError("Error occurred during the test execution", e);
        }

    }

    private Iterable<Class<?>> loadClasses(LauncherOptions options) {
        try {
            var loader = servicesProducer.loader(options);

            return loader.load();
        } catch (Exception e) {
            handleError("Error occurred during the class loading", e);
            return null;
        }
    }

    private void handleError(String message, Exception e) {
        System.err.println(message);
        e.printStackTrace();
        throw new RuntimeException(e);
    }
}
