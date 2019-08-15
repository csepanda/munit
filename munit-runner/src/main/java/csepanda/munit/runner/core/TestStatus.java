package csepanda.munit.runner.core;

/**
 * Represents statuses of test before and after attempt to run.
 */
public enum TestStatus {
    /**
     * Test wasn't run. This status is not used yet.
     */
    NONE,

    /**
     * Test wasn't executed because it is imperatively ignored.
     */
    IGNORED,

    /**
     * Test was successfully executed without any errors.
     */
    SUCCESS,

    /**
     * Test failed during the execution due to exception or fail of assertion.
     */
    FAILED,

    /**
     * Test wasn't run due to the invocation exception.
     */
    NOT_RUNNED
}
