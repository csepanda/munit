package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.core.TestResult;
import csepanda.munit.runner.core.TestStatus;
import csepanda.munit.runner.services.IExecutor;
import csepanda.munit.runner.services.ITestClassBuilder;

import java.util.ArrayList;

public class SimpleExecutor implements IExecutor {
    private ITestClassBuilder builder;

    public SimpleExecutor(ITestClassBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Iterable<TestResult> execute(final ITestPlan testPlan) {
        var results = new ArrayList<TestResult>();
        var plan = testPlan.getPlan();

        for (var test : plan) {
            try {
                var object = builder.build(test.getTestClass());
                var method = test.getTestMethod();

                method.invoke(object);
                results.add(success(test));
            } catch (ReflectiveOperationException e) {
                // TODO add stack trace analysis here to avoid invalid test's status
                results.add(notRunned(test, e));
            } catch (Exception e) {
                results.add(failed(test, e));
            }
        }

        return results;
    }

    private TestResult success(TestPlanRecord test) {
        return new TestResult(TestStatus.SUCCESS, test);
    }

    private TestResult failed(TestPlanRecord test, Exception exception) {
       return new TestResult(TestStatus.FAILED, test, exception);
    }

    private TestResult notRunned(TestPlanRecord test, Exception exception) {
        return new TestResult(TestStatus.NOT_RUNNED, test, exception);
    }
}
