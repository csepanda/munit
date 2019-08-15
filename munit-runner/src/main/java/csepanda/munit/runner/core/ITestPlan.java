package csepanda.munit.runner.core;


public interface ITestPlan {
    Iterable<TestPlanRecord> getPlan();

    long getPlanSize();

    void addTest(TestPlanRecord record);
}
