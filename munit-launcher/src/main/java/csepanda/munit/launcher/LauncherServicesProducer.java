package csepanda.munit.launcher;

import csepanda.munit.launcher.loaders.ILoader;
import csepanda.munit.launcher.loaders.JarLoader;
import csepanda.munit.launcher.options.LauncherOptions;
import csepanda.munit.runner.services.*;
import csepanda.munit.runner.services.concurrent.ConcurrentExecutor;
import csepanda.munit.runner.services.simple.NoArgsTestClassBuilder;
import csepanda.munit.runner.services.simple.Executor;
import csepanda.munit.runner.services.simple.TestPlanner;

import java.io.IOException;
import java.util.jar.JarFile;

class LauncherServicesProducer {
    public ILoader loader(LauncherOptions options) throws IOException {
        var sourceType = options.getSourceType();

        switch (sourceType) {
            case JAR:
                return new JarLoader(new JarFile(options.getSourcesPath()));
            case CLASS:
            case DIRECTORY:
                throw new UnsupportedOperationException("Unsupported source type: " + sourceType);
            default:
                throw new IllegalStateException("Unexpected value: " + sourceType);
        }
    }
    public ILauncher launcher(LauncherOptions options) {
        var runMode = options.getRunMode();

        switch (runMode) {
            case SIMPLE:
            case CONCURRENT:
                return new csepanda.munit.runner.services.Launcher(testPlanner(options), executor(options));
            default:
                throw new IllegalStateException("Unexpected value: " + options.getRunMode());
        }
    }

    public ITestPlanner testPlanner(LauncherOptions options) {
        var runMode = options.getRunMode();

        switch (runMode) {
            case SIMPLE:
            case CONCURRENT:
                return new TestPlanner();
            default:
                throw new IllegalStateException("Unexpected value: " + options.getRunMode());
        }
    }

    public IExecutor executor(LauncherOptions options) {
        var runMode = options.getRunMode();

        switch (runMode) {
            case SIMPLE:
                return new Executor(classBuilder(options));
            case CONCURRENT:
                return new ConcurrentExecutor(classBuilder(options));
            default:
                throw new IllegalStateException("Unexpected value: " + runMode);
        }
    }

    public ITestClassBuilder classBuilder(LauncherOptions options) {
        return new NoArgsTestClassBuilder();
    }
}
