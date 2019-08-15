package csepanda.munit.runner.core;

/**
 * ITestPlan is an interface for POJO object which provide methods for plan building and retrieving.
 */
public interface ITestPlan {
    /**
     * Returns iterable that contains test plan records.
     *
     * @return iterable with test plan records.
     */
    Iterable<TestPlanRecord> getPlan();

    /**
     * Returns count of test plan records.
     *
     * @return positive integer that represent amount of test plan records in this test plan.
     */
    int getPlanSize();

    /**
     * Add new test plan record to the test plan.
     *
     * @param record TestPlanRecord that will be added to this test plan.
     */
    void addTest(TestPlanRecord record);
}
