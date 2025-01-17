package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.services.ITestPlanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static csepanda.munit.runner.services.simple.utils.Helpers.iterableToArray;

public class TestPlannerTests_EmptyInput {
    private ITestPlanner planner;

    @Before
    public void setUp() {
        this.planner = new TestPlanner();
    }

    @Test
    public void emptyIterableInput() {
        var plan = planner.plan(List.of());
        var actualResults = iterableToArray(plan.getPlan());

        Assert.assertEquals("Plan from EmptyClass should not provide filled plan", 0, actualResults.size());
    }

    @Test
    public void emptyClass() {
        class EmptyClass {
        }

        var plan = planner.plan(List.of(EmptyClass.class));
        var actualResults = iterableToArray(plan.getPlan());

        Assert.assertEquals("Plan from EmptyClass should not provide filled plan", 0, actualResults.size());
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullInput() {
        planner.plan(null);
    }

    @Test
    public void classWithoutTestAnnotations() {
        class ClassWithoutAnnotations {
            @SuppressWarnings({"unused", "EmptyMethod"})
            public void foo() {
            }

            @SuppressWarnings({"unused", "EmptyMethod"})
            public void bar() {
            }
        }

        var plan = planner.plan(List.of(ClassWithoutAnnotations.class));
        var actualResults = iterableToArray(plan.getPlan());

        Assert.assertEquals("Plan from ClassWithoutAnnotations should not provide filled plan", 0, actualResults.size());
    }
}
