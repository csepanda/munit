package csepanda.munit.runner.services;

import csepanda.munit.runner.core.ITestPlan;

public interface ITestPlanner {
    ITestPlan plan(Iterable<Class<?>> classes);
}
