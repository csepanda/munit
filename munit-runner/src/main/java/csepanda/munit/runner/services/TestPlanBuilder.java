package csepanda.munit.runner.services;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.core.simple.SimpleTestPlan;

public class TestPlanBuilder {
    public ITestPlan build(Iterable<Class<?>> classes) {
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
