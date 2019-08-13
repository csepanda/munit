package csepanda.munit.runner.services;

import csepanda.munit.runner.core.TestResult;

public interface ILauncher {
    Iterable<TestResult> launch(Iterable<Class<?>> classes);
}
