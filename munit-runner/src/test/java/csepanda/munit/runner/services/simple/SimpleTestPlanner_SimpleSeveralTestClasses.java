package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.services.ITestPlanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

@RunWith(Parameterized.class)
public class SimpleTestPlanner_SimpleSeveralTestClasses {
    private List<TestPlanRecord> plan;

    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(new Object[]{"foo", FirstTestClass.class}, new Object[]{"bar", SecondTestClass.class});
    }

    @Parameterized.Parameter
    public String methodName;

    @Parameterized.Parameter(1)
    public Class<?> testClass;

    @Before
    public void setUp() {
        ITestPlanner planner = new SimpleTestPlanner();

        var plan = planner.plan(List.of(FirstTestClass.class, SecondTestClass.class));
        this.plan = iterableToArray(plan.getPlan());
    }

    @Test
    public void assertTestMethodCount() {
        Assert.assertEquals(2, plan.size());

        var fooTest = findByPredicate(plan, x -> x.getTestMethod().getName().equals("foo"));
        var barTest = findByPredicate(plan, x -> x.getTestMethod().getName().equals("foo"));

        Assert.assertNotNull(fooTest);
        Assert.assertNotNull(barTest);
    }

    @Test
    public void verifyTestClass() {
        var fooTest = findByPredicate(plan, x -> x.getTestMethod().getName().equals(this.methodName));

        Assert.assertNotNull(fooTest);
        Assert.assertEquals(testClass, fooTest.getTestClass());
    }

    @Test
    public void verifyTestMethod() throws NoSuchMethodException {
        var fooTest = findByPredicate(plan, x -> x.getTestMethod().getName().equals(this.methodName));
        var expectedMethod = testClass.getMethod(this.methodName);

        Assert.assertNotNull(fooTest);
        Assert.assertEquals(expectedMethod, fooTest.getTestMethod());
    }

    @Test
    public void verifyTestName() throws NoSuchMethodException {
        var fooTest = findByPredicate(plan, x -> x.getTestMethod().getName().equals(this.methodName));
        var method = testClass.getMethod(this.methodName);
        var expectedName = testClass.getName() + " - " + method.getName();

        Assert.assertNotNull(fooTest);
        Assert.assertEquals(expectedName, fooTest.getName());
    }

    private <T> T findByPredicate(List<T> source, Predicate<T> predicate) {
        return source.stream()
            .filter(predicate)
            .findAny()
            .orElse(null);
    }

    private List<TestPlanRecord> iterableToArray(Iterable<TestPlanRecord> planRecords) {
        var resultingList = new ArrayList<TestPlanRecord>();

        planRecords.forEach(resultingList::add);
        return resultingList;
    }

    class FirstTestClass
    {
        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void foo() { }
    }

    class SecondTestClass
    {
        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void bar() { }
    }
}