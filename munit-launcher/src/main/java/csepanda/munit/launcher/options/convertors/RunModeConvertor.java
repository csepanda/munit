package csepanda.munit.launcher.options.convertors;

import com.beust.jcommander.IStringConverter;
import csepanda.munit.launcher.options.RunMode;

public class RunModeConvertor implements IStringConverter<RunMode> {
    @Override
    public RunMode convert(String s) {
        switch (s) {
            case "simple":
                return RunMode.SIMPLE;
            case "concurrent":
                return RunMode.CONCURRENT;
            default:
                throw new IllegalStateException("Unsupported run mode: " + s);
        }
    }
}
