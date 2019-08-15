package csepanda.munit.launcher.loaders.utils;

public class FileNameUtils {
    private static final String CLASS_FILE_SUFFIX = ".class";

    public static boolean isClass(String filename) {
        return filename.endsWith(CLASS_FILE_SUFFIX);
    }

    /**
     * Transform passed filePath to fully-qualified class name.
     *
     * <p>
     * Example:
     * <pre>
     * foo/bar/snafu.class -> foo.bar.snafu
     *     </pre>
     * </p>
     *
     * @param filePath path to the file, should not be started from slash or dot
     * @return fully-qualified class name
     */
    public static String convertToClassName(String filePath) {
        if (filePath.startsWith(".") || filePath.startsWith("/")) {
            throw new IllegalArgumentException("filePath should not be started from slash or dot");
        }

        return filePath.substring(0, filePath.length() - CLASS_FILE_SUFFIX.length())
            .replace('/', '.');
    }
}
