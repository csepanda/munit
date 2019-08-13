package csepanda.munit.runner.core;


public interface ITestPlan {
    Iterable<TestPlanRecord> getPlan();

    void addTest(TestPlanRecord record);
}
