package csepanda.munit.runner.services.concurrent;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestResult;
import csepanda.munit.runner.services.ExecutorBase;
import csepanda.munit.runner.services.IExecutor;
import csepanda.munit.runner.services.ITestClassBuilder;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentExecutor extends ExecutorBase implements IExecutor  {
    private int maxThreadCount;

    public ConcurrentExecutor(ITestClassBuilder builder) {
        super(builder);
        this.maxThreadCount = Runtime.getRuntime().availableProcessors();
    }

    @Override
    public Iterable<TestResult> execute(final ITestPlan testPlan) {
        if (testPlan == null) {
            throw new IllegalArgumentException("testPlan should not be null");
        }

        var results = new ConcurrentLinkedQueue<TestResult>();
        var plan = testPlan.getPlan();

        var executor = Executors.newFixedThreadPool(this.maxThreadCount);

        for (var test : plan) {
            executor.execute(() -> {
                results.add(super.executeOne(test));
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return results;
    }
}
