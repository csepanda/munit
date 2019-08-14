package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.core.ITestPlan;
import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.core.TestResult;
import csepanda.munit.runner.core.TestStatus;
import csepanda.munit.runner.services.ITestClassBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import static org.mockito.Mockito.*;

public class SimpleExecutorTest {
    private ITestClassFramework testClass;
    private ITestClassBuilder objectBuilder;
    private Method successMethod;
    private Method failMethod;
    private Method invoicationIssueMethod;

    @Before
    public void setUp() throws ReflectiveOperationException {
        this.testClass = mock(ITestClassFramework.class);
        this.objectBuilder = mock(ITestClassBuilder.class);

        when(objectBuilder.build(ITestClassFramework.class)).thenReturn(this.testClass);

        doThrow(new RuntimeException()).when(testClass).fail();

        this.successMethod = testClass.getClass().getMethod("success");
        this.failMethod = testClass.getClass().getMethod("fail");
        this.invoicationIssueMethod = testClass.getClass().getMethod("invocationIssue", Object.class);
    }

    @Test
    public void successSingleTest() {
        var executor = new SimpleExecutor(objectBuilder);
        var testPlanMock = mock(ITestPlan.class);

        when(testPlanMock.getPlan()).thenReturn(Collections.singletonList(
            new TestPlanRecord(this.successMethod, ITestClassFramework.class)
        ));

        var testResults = executor.execute(testPlanMock);
        var actualResults = new ArrayList<TestResult>();

        testResults.forEach(actualResults::add);

        Assert.assertEquals("Only one result should be received", 1, actualResults.size());
        Assert.assertEquals("Result should be success", TestStatus.SUCCESS, actualResults.get(0).getStatus());

        invocationCountHelper(1, 0, 0);
    }

    @Test
    public void failSingleTest() {
        var executor = new SimpleExecutor(objectBuilder);
        var testPlanMock = mock(ITestPlan.class);

        when(testPlanMock.getPlan()).thenReturn(Collections.singletonList(
            new TestPlanRecord(this.failMethod, ITestClassFramework.class)
        ));

        var testResults = executor.execute(testPlanMock);
        var actualResults = new ArrayList<TestResult>();

        testResults.forEach(actualResults::add);

        Assert.assertEquals("Only one result should be received", 1, actualResults.size());
        Assert.assertEquals("Result should be fail", TestStatus.FAILED, actualResults.get(0).getStatus());

        invocationCountHelper(0, 1, 0);
    }

    @Test
    public void invocationIssueSingleTest() {
        var executor = new SimpleExecutor(objectBuilder);
        var testPlanMock = mock(ITestPlan.class);

        when(testPlanMock.getPlan()).thenReturn(Collections.singletonList(
            new TestPlanRecord(this.invoicationIssueMethod, ITestClassFramework.class)
        ));

        var testResults = executor.execute(testPlanMock);
        var actualResults = new ArrayList<TestResult>();

        testResults.forEach(actualResults::add);

        Assert.assertEquals("Only one result should be received", 1, actualResults.size());
        Assert.assertEquals("Result should be fail", TestStatus.NOT_RUNNED, actualResults.get(0).getStatus());

        invocationCountHelper(0, 0, 0);
    }

    private void invocationCountHelper(int success, int fail, int invocationIssue) {
        verify(testClass, times(success)).success();
        verify(testClass, times(fail)).fail();
        verify(testClass, times(invocationIssue)).invocationIssue(any());
    }

    interface ITestClassFramework {
        void success();

        void invocationIssue(Object arg);

        void fail();

    }
}