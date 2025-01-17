package csepanda.munit.runner.services.simple;

import org.junit.Test;

import static org.junit.Assert.*;

public class NoArgsTestClassBuilderTests {

    @Test
    public void buildClass() throws ReflectiveOperationException {
        var builder = new NoArgsTestClassBuilder();

        var object = builder.build(Object.class);

        assertNotNull(object);
    }

    @Test(expected = ReflectiveOperationException.class)
    public void failOnConstructorWithArgs() throws ReflectiveOperationException {
        class TestClass {
            public TestClass(@SuppressWarnings("unused") Object arg) {

            }
        }

        var builder = new NoArgsTestClassBuilder();
        builder.build(TestClass.class);
    }

}
