package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.core.simple.SimpleTestPlan;
import csepanda.munit.runner.services.ITestPlanner;
import csepanda.munit.runner.services.TestAnnotationFilter;

public class SimpleTestPlanner implements ITestPlanner {
    public ITestPlan plan(Iterable<Class<?>> classes) {
        if (classes == null) {
            throw new IllegalArgumentException("classes should not be null");
        }

        var testPlan = new SimpleTestPlan();
        var annotationFilter = new TestAnnotationFilter();

        for (var clazz : classes) {
            var testMethods = annotationFilter.filterTests(clazz);

            for (var testMethod : testMethods) {
                testPlan.addTest(new TestPlanRecord(testMethod, clazz));
            }
        }

        return testPlan;
    }
}
