package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestResult;
import csepanda.munit.runner.services.ExecutorBase;
import csepanda.munit.runner.services.IExecutor;
import csepanda.munit.runner.services.ITestClassBuilder;

import java.util.ArrayList;

public class Executor extends ExecutorBase implements IExecutor {

    public Executor(ITestClassBuilder builder) {
        super(builder);
    }

    @Override
    public Iterable<TestResult> execute(final ITestPlan testPlan) {
        if (testPlan == null) {
            throw new IllegalArgumentException("testPlan should not be null");
        }

        var results = new ArrayList<TestResult>((int) testPlan.getPlanSize());
        var plan = testPlan.getPlan();

        for (var test : plan) {
            results.add(super.executeOne(test));
        }

        return results;
    }
}
