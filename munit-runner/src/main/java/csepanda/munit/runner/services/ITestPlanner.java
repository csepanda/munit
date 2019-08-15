package csepanda.munit.runner.services;

import csepanda.munit.runner.core.ITestPlan;

/**
 * Service that discover tests in specified classes.
 */
public interface ITestPlanner {
    /**
     * Discovers tests in specified class and builds test plan.
     *
     * @param classes classes that will be searched for tests.
     * @return test plan built from discovered tests.
     */
    ITestPlan plan(Iterable<Class<?>> classes);
}
