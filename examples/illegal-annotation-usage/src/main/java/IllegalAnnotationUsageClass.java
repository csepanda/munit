import csepanda.munit.annotation.Test;

public class IllegalAnnotationUsageClass {
    @Test
    private void privateMethod() {}

    @Test
    protected void protectedMethod() {}

    @Test
    void packageMethod() {}

    @Test
    public Object nonVoidReturnType() {return null;}

    @Test
    public void methodWithArguments(Object foo) {}

}
