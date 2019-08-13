package csepanda.munit.runner.services;

import csepanda.munit.runner.core.TestResult;

public class Launcher implements ILauncher {
    private final ITestPlanner planner;
    private final IExecutor executor;

    public Launcher(ITestPlanner planner, IExecutor executor) {
        this.planner = planner;
        this.executor = executor;
    }

    @Override
    public Iterable<TestResult> launch(Iterable<Class<?>> classes) {
        var plan = planner.plan(classes);
        var results = executor.execute(plan);

        return results;
    }
}