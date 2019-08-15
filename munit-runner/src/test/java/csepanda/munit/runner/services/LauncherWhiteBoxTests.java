package csepanda.munit.runner.services;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestResult;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class LauncherWhiteBoxTests {
    @Test(expected = IllegalArgumentException.class)
    public void nullArgument() {
        ITestPlanner mockPlanner = mock(ITestPlanner.class);
        IExecutor mockExecutor = mock(IExecutor.class);

        var launcher = new Launcher(mockPlanner, mockExecutor);

        launcher.launch(null);
    }

    @Test
    public void verifyThePipeline() {
        ITestPlanner mockPlanner = mock(ITestPlanner.class);
        IExecutor mockExecutor = mock(IExecutor.class);
        ITestPlan mockTestPlan = mock(ITestPlan.class);
        IterableInput input = mock(IterableInput.class);
        IterableResult result = mock(IterableResult.class);

        when(mockPlanner.plan(any())).thenReturn(mockTestPlan);
        when(mockExecutor.execute(any())).thenReturn(result);

        var launcher = new Launcher(mockPlanner, mockExecutor);

        var actual = launcher.launch(input);

        verify(mockPlanner, times(1)).plan(input);
        verify(mockExecutor, times(1)).execute(mockTestPlan);

        Assert.assertEquals(result, actual);
    }

    private interface IterableInput extends Iterable<Class<?>> {}
    private interface IterableResult extends Iterable<TestResult> {}
}