package csepanda.munit.launcher.options;

/**
 * Determines type of the tests sources.
 */
public enum SourceType {
    /**
     * Tests will be discovered in specified jar file.
     */
    JAR,

    /**
     * Tests will be discovered in specified class file.
     */
    CLASS,

    /**
     * Specified directory will be scanned for class files and tests will be discovered in those classes.
     */
    DIRECTORY
}
