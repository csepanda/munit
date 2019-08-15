package csepanda.munit.runner.core;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * Represents pair of test class and its test method with specified name.
 */
public class TestPlanRecord {
    private final Class<?> testClass;
    private final Method testMethod;
    private final String testName;

    /**
     * Constructs new TestPlanRecord with specified testClass and its test method.
     *
     * @param testMethod method that will be executed during the testing
     * @param testClass  testClass that contains specified testMethod
     */
    public TestPlanRecord(Method testMethod, Class<?> testClass) {
        this.testClass = testClass;
        this.testMethod = testMethod;

        this.testName = testClass.getName() + " - " + testMethod.getName();
    }

    /**
     * Returns name of the test.
     *
     * @return by default collocation of test class and methods names separated by ' - '
     */
    public String getName() {
        return testName;
    }

    /**
     * Returns test class.
     *
     * @return Class from which this test plan record was created.
     */
    public Class<?> getTestClass() {
        return testClass;
    }

    /**
     * Returns test method.
     *
     * @return Method that will be invoked to run this test.
     */
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
