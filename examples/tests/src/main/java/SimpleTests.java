import csepanda.munit.annotation.Test;

public class SimpleTests {
    @Test
    public void fooTest() {
        System.out.println("Method fooTest from SimpleTests is called");
    }

    public void foo() {
        System.out.println("Method foo is not called");
    }
}
