package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.services.ITestPlanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static csepanda.munit.runner.services.simple.utils.Helpers.findByPredicate;
import static csepanda.munit.runner.services.simple.utils.Helpers.iterableToArray;

@RunWith(Parameterized.class)
public class TestPlannerTests_SeveralTestClasses {
    private List<TestPlanRecord> plan;

    @Parameterized.Parameters
    public static Collection<Object[]> cases() {
        return Arrays.asList(
            new Object[]{"foo", TestClassesData.SingleFooMethod.class},
            new Object[]{"bar", TestClassesData.SingleBarMethod.class}
        );
    }

    @Parameterized.Parameter
    public String methodName;

    @Parameterized.Parameter(1)
    public Class<?> testClass;

    @Before
    public void setUp() {
        ITestPlanner planner = new TestPlanner();

        var plan = planner.plan(List.of(TestClassesData.SingleFooMethod.class, TestClassesData.SingleBarMethod.class));
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
}