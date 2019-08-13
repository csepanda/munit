package csepanda.munit.runner.services;

import csepanda.munit.annotation.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

public class TestAnnotationFilter {
    public Iterable<Method> filterTests(Class<?> clazz) {
        var methods = clazz.getMethods();

        return Arrays.stream(methods)
                .filter(TestAnnotationFilter::annotationCheck)
                .collect(Collectors.toList());
    }

    private static boolean annotationCheck(Method method) {
        return method.isAnnotationPresent(Test.class);
    }
}
