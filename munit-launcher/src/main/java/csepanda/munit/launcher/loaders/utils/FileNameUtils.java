package csepanda.munit.launcher.loaders.utils;

public class FileNameUtils {
    private static final String CLASS_FILE_SUFFIX = ".class";

    public static boolean isClass(String filename) {
        return filename.endsWith(CLASS_FILE_SUFFIX);
    }

    public static String convertToClassName(String filePath) {
        return filePath.substring(0, filePath.length() - CLASS_FILE_SUFFIX.length())
                .replace('/', '.');
    }
}
