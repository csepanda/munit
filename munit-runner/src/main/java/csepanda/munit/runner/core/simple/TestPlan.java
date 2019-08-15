package csepanda.munit.runner.core.simple;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestPlanRecord;

import java.util.ArrayList;
import java.util.List;

public class TestPlan implements ITestPlan {
    private final List<TestPlanRecord> plan;

    public TestPlan() {
        this.plan = new ArrayList<>();
    }

    @Override
    public Iterable<TestPlanRecord> getPlan() {
        return plan;
    }

    @Override
    public int getPlanSize() {
        return plan.size();
    }

    @Override
    public void addTest(TestPlanRecord record) {
        this.plan.add(record);
    }
}
