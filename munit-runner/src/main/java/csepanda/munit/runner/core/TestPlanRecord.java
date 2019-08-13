package csepanda.munit.runner.core;

import java.lang.reflect.Method;
import java.util.Objects;

public class TestPlanRecord {
    private final Class<?> testClass;
    private final Method testMethod;
    private final String testName;

    public TestPlanRecord(Method testMethod, Class<?> testClass) {
        this.testClass = testClass;
        this.testMethod = testMethod;

        this.testName = testClass.getName() + " - " + testMethod.getName();
    }

    public String getName() {
        return testName;
    }

    public Class<?> getTestClass() {
        return testClass;
    }

    public Method getTestMethod() {
        return testMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestPlanRecord that = (TestPlanRecord) o;
        return testClass.equals(that.testClass) &&
                testMethod.equals(that.testMethod);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testClass, testMethod);
    }
}
