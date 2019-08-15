package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.core.TestResult;
import csepanda.munit.runner.core.TestStatus;
import csepanda.munit.runner.services.IExecutor;
import csepanda.munit.runner.services.ITestClassBuilder;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Executor implements IExecutor {
    private ITestClassBuilder builder;

    public Executor(ITestClassBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Iterable<TestResult> execute(final ITestPlan testPlan) {
        if (testPlan == null) {
            throw new IllegalArgumentException("testPlan should not be null");
        }

        var results = new ArrayList<TestResult>();
        var plan = testPlan.getPlan();

        for (var test : plan) {
            try {
                var object = builder.build(test.getTestClass());
                var method = test.getTestMethod();

                method.invoke(object);
                results.add(success(test));
            } catch (InvocationTargetException e) {
                results.add(failed(test, e.getTargetException()));
            } catch (Exception e) {
                results.add(notRunned(test, e));
            }
        }

        return results;
    }

    private TestResult success(TestPlanRecord test) {
        return new TestResult(TestStatus.SUCCESS, test);
    }

    private TestResult failed(TestPlanRecord test, Throwable exception) {
       return new TestResult(TestStatus.FAILED, test, exception);
    }

    private TestResult notRunned(TestPlanRecord test, Throwable exception) {
        return new TestResult(TestStatus.NOT_RUNNED, test, exception);
    }
}
