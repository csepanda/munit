package csepanda.munit.runner.services;

import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.core.TestResult;
import csepanda.munit.runner.core.TestStatus;

import java.lang.reflect.InvocationTargetException;

public abstract class ExecutorBase implements IExecutor {
    private ITestClassBuilder builder;

    public ExecutorBase(ITestClassBuilder builder) {
        this.builder = builder;
    }

    protected TestResult executeOne(TestPlanRecord test) {
        try {
            var object = builder.build(test.getTestClass());
            var method = test.getTestMethod();

            method.invoke(object);
            return success(test);
        } catch (InvocationTargetException e) {
            return failed(test, e.getTargetException());
        } catch (Exception e) {
            return notRunned(test, e);
        }
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
