import csepanda.munit.annotation.Test;

public class TestsInheritance extends SuperTests {
    @Test
    public void derivedClassMethod() {
        System.out.println("Method derivedClassMethod is called from TestsInheritance");
    }

    @Test
    @Override
    public void superFooTest() {
        System.out.println("Method superFooTest is called from TestsInheritance");
    }
}
