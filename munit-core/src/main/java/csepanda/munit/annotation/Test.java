package csepanda.munit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** The @Test annotation is used to specify methods that will be run by mUnit.
 *
 * This annotation can be applied only to public void methods without arguments.
 *
 * Example of usage:
 * <pre>{@code
 *     public class ExampleTestClass {
 *          @Test
 *          public void exampleTest() {
 *              var expected = 4;
 *              var actual =  2 + 2;
 *
 *              if (expected != actual) {
 *                  throw new RuntimeException("Expected " + expected + " but got " + actual);
 *              }
 *
 *          }
 *     }
 * }</pre>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Test {
}
