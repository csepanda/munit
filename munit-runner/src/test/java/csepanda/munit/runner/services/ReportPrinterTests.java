package csepanda.munit.runner.services;

import csepanda.munit.runner.core.TestPlanRecord;
import csepanda.munit.runner.core.TestResult;
import csepanda.munit.runner.core.TestStatus;
import org.junit.Test;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.*;

public class ReportPrinterTests {

    @Test
    public void testPossibleTestStatuses() { // Should not throw
        var testRecordMock = mock(TestPlanRecord.class);
        var outputMock = mock(PrintStream.class);

        var input = Arrays.stream(TestStatus.values())
            .map(x -> new TestResult(x, testRecordMock))
            .collect(Collectors.toList());

        var printer = new ReportPrinter(outputMock);

        printer.print(input);
    }

    @Test
    public void printStackTraceInVerboseMode() {
        var testRecordMock = mock(TestPlanRecord.class);
        var outputMock = mock(PrintStream.class);
        var exceptionMock = mock(Throwable.class);

        var input = List.of(new TestResult(TestStatus.FAILED, testRecordMock, exceptionMock));

        var printer = new ReportPrinter(outputMock, true);

        printer.print(input);

        verify(exceptionMock, times(1)).printStackTrace(outputMock);
    }

    @Test
    public void doNotPrintStackTraceInNoNVerboseMode() {
        var testRecordMock = mock(TestPlanRecord.class);
        var outputMock = mock(PrintStream.class);
        var exceptionMock = mock(Throwable.class);

        var input = List.of(new TestResult(TestStatus.FAILED, testRecordMock, exceptionMock));

        var printer = new ReportPrinter(outputMock, false);

        printer.print(input);

        verify(exceptionMock, times(0)).printStackTrace(any(PrintStream.class));
        verify(exceptionMock, times(0)).printStackTrace(any(PrintWriter.class));
        verify(exceptionMock, times(0)).printStackTrace();
    }

}