package csepanda.munit.runner.core;

public class TestResult {
    private final TestStatus status;
    private final TestPlanRecord test;
    private final Throwable exception;

    public TestResult(TestStatus status, TestPlanRecord test, Throwable exception) {
        this.status = status;
        this.test = test;
        this.exception = exception;
    }

    public TestResult(TestStatus status, TestPlanRecord test) {
        this.status = status;
        this.test = test;
        this.exception = null;
    }

    public TestStatus getStatus() {
        return status;
    }

    public TestPlanRecord getTest() {
        return test;
    }

    public Throwable getException() {
        return exception;
    }
}
