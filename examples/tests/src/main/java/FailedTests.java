import csepanda.munit.annotation.Test;

public class FailedTests {
    @Test
    public void testWithException() {
        throw new RuntimeException();
    }

    @Test
    public void testWithNativeAssert() {
        if (!FailedTests.class.desiredAssertionStatus()) {
            System.err.println("Warning: assertions are not enabled. This tests will have SUCCESS status. " +
                "Please pass -ea argument to java to fail this test");
        }

        assert false;
    }
}
