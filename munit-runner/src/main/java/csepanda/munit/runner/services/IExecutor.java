package csepanda.munit.runner.services;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestResult;

public interface IExecutor {
    Iterable<TestResult> execute(final ITestPlan testPlan);
}
