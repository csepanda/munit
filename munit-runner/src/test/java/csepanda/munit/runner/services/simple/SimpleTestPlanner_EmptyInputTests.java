package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.services.ITestPlanner;
import csepanda.munit.runner.services.simple.SimpleTestPlanner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SimpleTestPlanner_EmptyInputTests {
    private ITestPlanner planner;

    @Before
    public void setUp() {
        this.planner = new SimpleTestPlanner();
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

    @Test
    public void classWithoutTestAnnotations() {
        class ClassWithoutAnnotations {
            public void foo() {
            }

            public void bar() {
            }
        }

        var plan = planner.plan(List.of(ClassWithoutAnnotations.class));
        var actualResults = iterableToArray(plan.getPlan());

        Assert.assertEquals("Plan from ClassWithoutAnnotations should not provide filled plan", 0, actualResults.size());
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
}
