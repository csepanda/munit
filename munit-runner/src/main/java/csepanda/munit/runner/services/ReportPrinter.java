package csepanda.munit.runner.services;

import csepanda.munit.runner.core.TestResult;

import java.io.PrintStream;

/**
 * Implementation of IReportPrinter that prints report to the provided PrintStream.
 */
public class ReportPrinter implements IReportPrinter {
    private final PrintStream out;
    private final boolean verbose;

    /**
     * Constructs instance of ReportPrinter.
     *
     * @param out PrintStream where report will be printed.
     * @param verbose if true then exceptions stacktrace will be printed to out.
     */
    public ReportPrinter(PrintStream out, boolean verbose) {
        this.out = out;
        this.verbose = verbose;
    }

    /**
     * Constructs instance of non-verbose ReportPrinter that doesn't print stacktrace of throwed exceptions
     *
     * @param out PrintStream where report will be printed.
     */
    public ReportPrinter(PrintStream out) {
        this.out = out;
        this.verbose = false;
    }

    @Override
    public void print(Iterable<TestResult> results) {
        int success = 0, failed = 0, notRun = 0;

        for (var result : results) {
            var testName = result.getTest().getName();

            out.println(testName + " " + result.getStatus());
            if (verbose && result.getException() != null) {
                result.getException().printStackTrace(out);
            }

            switch (result.getStatus()) {
                case NONE:
                case IGNORED:
                case NOT_RUNNED:
                    notRun++;
                    break;
                case SUCCESS:
                    success++;
                    break;
                case FAILED:
                    failed++;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + result.getStatus());
            }
        }

        out.println("[SUMMARY] success: " + success + ", failed: " + failed + ", notRun: " + notRun);
    }
}
