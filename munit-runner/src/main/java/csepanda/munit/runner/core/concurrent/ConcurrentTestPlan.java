package csepanda.munit.runner.core.concurrent;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestPlanRecord;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentTestPlan implements ITestPlan {
    private final Map<String, TestPlanRecord> plan;

    public ConcurrentTestPlan() {
        this.plan = new ConcurrentHashMap<>();
    }

    @Override
    public Iterable<TestPlanRecord> getPlan() {
        return plan.values();
    }

    @Override
    public int getPlanSize() {
        return plan.size();
    }

    public void addTest(TestPlanRecord record) {
        // as record's name should be unique
        this.plan.put(record.getName(), record);
    }
}
