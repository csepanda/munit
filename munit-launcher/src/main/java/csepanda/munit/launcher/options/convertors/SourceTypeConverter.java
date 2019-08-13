package csepanda.munit.launcher.options.convertors;

import com.beust.jcommander.IStringConverter;
import csepanda.munit.launcher.options.RunMode;
import csepanda.munit.launcher.options.SourceType;

public class SourceTypeConverter implements IStringConverter<SourceType> {
    @Override
    public SourceType convert(String s) {
        switch (s) {
            case "jar":
                return SourceType.JAR;
            case "dir":
            case "directory":
                return SourceType.DIRECTORY;
            case "class":
                return SourceType.CLASS;
            default:
                throw new IllegalStateException("Unsupported run mode: " + s);
        }
    }
}
