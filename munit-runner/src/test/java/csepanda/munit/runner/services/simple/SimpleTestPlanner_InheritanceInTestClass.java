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
public class SimpleTestPlanner_InheritanceInTestClass {
    private List<TestPlanRecord> plan;
    private TestPlanRecord currentPlanRecord;

    @Parameterized.Parameters(name = "{index}: {0}, {2}")
    public static Collection<Object[]> cases() {
        return Arrays.asList(
            new Object[]{"foo", SimpleTestClass.class, SimpleTestClass.class.getSimpleName()},
            new Object[]{"bar", SimpleTestClass.class, SimpleTestClass.class.getSimpleName()},
            new Object[]{"foobar", SimpleTestClass.class, SimpleTestClass.class.getSimpleName()},
            new Object[]{"foo", SuperTestClass.class, SuperTestClass.class.getSimpleName()},
            new Object[]{"foobar", SuperTestClass.class, SuperTestClass.class.getSimpleName()}
        );
    }

    @Parameterized.Parameter
    public String methodName;

    @Parameterized.Parameter(1)
    public Class<?> testClass;

    @Parameterized.Parameter(2)
    public String className;

    @Before
    public void setUp() {
        ITestPlanner planner = new SimpleTestPlanner();

        var plan = planner.plan(List.of(SimpleTestClass.class, SuperTestClass.class));
        this.plan = iterableToArray(plan.getPlan());
        this.currentPlanRecord = findCurrentTest();
    }

    @Test
    public void assertTestMethodCount() {
        Assert.assertEquals(5, plan.size());

        var fooTest = findByPredicate(plan, x -> x.getTestMethod().getName().equals("foo"));
        var barTest = findByPredicate(plan, x -> x.getTestMethod().getName().equals("foo"));

        Assert.assertNotNull(fooTest);
        Assert.assertNotNull(barTest);
    }

    @Test
    public void verifyTestClass() {
        Assert.assertNotNull(currentPlanRecord);
        Assert.assertEquals(testClass, currentPlanRecord.getTestClass());
    }

    @Test
    public void verifyTestMethod() throws NoSuchMethodException {
        var expectedMethod = testClass.getMethod(this.methodName);

        Assert.assertNotNull(currentPlanRecord);
        Assert.assertEquals(expectedMethod, currentPlanRecord.getTestMethod());
    }

    @Test
    public void verifyTestName() throws NoSuchMethodException {
        var method = testClass.getMethod(this.methodName);
        var expectedName = testClass.getName() + " - " + method.getName();

        Assert.assertNotNull(currentPlanRecord);
        Assert.assertEquals(expectedName, currentPlanRecord.getName());
    }

    private TestPlanRecord findCurrentTest() {
        return this.plan.stream()
            .filter(x -> x.getTestMethod().getName().equals(methodName) &&
                x.getTestClass().getSimpleName().equals(className))
            .findAny()
            .orElse(null);
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

    class SimpleTestClass extends SuperTestClass {
        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void foo() {
        }

        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void bar() {
        }
    }

    class SuperTestClass {
        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void foo() {
        }

        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void foobar() {
        }

    }
}