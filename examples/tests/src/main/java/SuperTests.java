import csepanda.munit.annotation.Test;

public class SuperTests {
    @Test
    public void superFooTest() {
        System.out.println("Method superFooTests is called from SuperTests");
    }

    @Test
    public void superBarTest() {
        System.out.println("Method superBarTest is called from SuperTests");
    }
}
