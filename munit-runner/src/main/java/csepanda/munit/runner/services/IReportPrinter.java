package csepanda.munit.runner.services;

import csepanda.munit.runner.core.TestResult;

public interface IReportPrinter {
    void print(Iterable<TestResult> results);
}
