package csepanda.munit.runner.services;

import csepanda.munit.runner.core.TestResult;

/**
 * Service that combine several other services to launch test discovery of passed tests, execute formed plan and
 * return results.
 */
public interface ILauncher {
    /**
     * Discover tests from specified classes and return the results of their execution.
     *
     * @param classes with tests that should be discovered.
     * @return results of test execution.
     */
    Iterable<TestResult> launch(Iterable<Class<?>> classes);
}
