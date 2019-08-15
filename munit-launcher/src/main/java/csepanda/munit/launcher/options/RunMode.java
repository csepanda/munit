package csepanda.munit.launcher.options;

/**
 * Determines test execution modes.
 */
public enum RunMode {
    /**
     * Tests will be executed in non-concurrent mode.
     */
    SIMPLE,

    /**
     * Tests will be executed concurrently.
     */
    CONCURRENT
}
