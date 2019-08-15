package csepanda.munit.runner.services;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestResult;

/**
 * Represents a service which executes provided test plan and returns result of execution.
 */
public interface IExecutor {
    /**
     * Executes provided test plan and returns result of this execution.
     *
     * @param testPlan that should be executed
     * @return results of test plan execution
     */
    Iterable<TestResult> execute(final ITestPlan testPlan);
}
