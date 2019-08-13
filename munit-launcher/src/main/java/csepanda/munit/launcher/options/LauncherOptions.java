package csepanda.munit.launcher.options;

import com.beust.jcommander.Parameter;
import csepanda.munit.launcher.options.convertors.RunModeConvertor;
import csepanda.munit.launcher.options.convertors.SourceTypeConverter;

public class LauncherOptions {
    @Parameter(names = {"--mode", "-m"})
    private RunMode runMode = RunMode.SIMPLE;

    @Parameter(names = {"--source-type", "-s"}, converter = SourceTypeConverter.class)
    private SourceType sourceType = SourceType.JAR;

    @Parameter(required = true)
    private String sourcesPath;

    @Parameter(names = {"--help", "-h"}, help = true)
    private boolean help;

    public RunMode getRunMode() {
        return runMode;
    }

    public void setRunMode(RunMode runMode) {
        this.runMode = runMode;
    }

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourcesPath() {
        return sourcesPath;
    }

    public void setSourcesPath(String sourcesPath) {
        this.sourcesPath = sourcesPath;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }
}
