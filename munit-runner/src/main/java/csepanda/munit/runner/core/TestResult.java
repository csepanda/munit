package csepanda.munit.runner.core;

/**
 * Represents result of the test's execution.
 */
public class TestResult {
    private final TestStatus status;
    private final TestPlanRecord test;
    private final Throwable exception;

    /**
     * Constructs new test result of specified test with specified status and exception.
     * If exception is not null that possibly mean that execution or test is failed.
     *
     * @param status status of test's execution
     * @param test test that was executed
     * @param exception that occurred during the execution
     */
    public TestResult(TestStatus status, TestPlanRecord test, Throwable exception) {
        this.status = status;
        this.test = test;
        this.exception = exception;
    }

    /**
     * Constructs new test result of specified test with specified status and null exception.
     *
     * @param status status of test's execution
     * @param test test that was executed
     */
    public TestResult(TestStatus status, TestPlanRecord test) {
        this.status = status;
        this.test = test;
        this.exception = null;
    }

    /**
     * Returns the status of test's execution.
     * @return status of test's execution.
     */
    public TestStatus getStatus() {
        return status;
    }

    /**
     * Returns the test that was executed.
     * @return test that was executed.
     */
    public TestPlanRecord getTest() {
        return test;
    }

    /**
     * Returns the throwable that was thrown during the test execution.
     * @return throwable that was thrown during the test execution.
     */
    public Throwable getException() {
        return exception;
    }
}
