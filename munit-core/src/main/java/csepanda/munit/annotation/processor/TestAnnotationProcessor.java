package csepanda.munit.annotation.processor;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Messager;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.type.NoType;
import javax.tools.Diagnostic;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import csepanda.munit.annotation.Test;

/**
 *  Simple test annotation processor that throw compile-time error if @Test annotation requirements are not met.
 *  Warning: doesn't provide any diagnostic information about errors
 */
public class TestAnnotationProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnvironment) {
        Messager messager = processingEnv.getMessager();

        for (var element : roundEnvironment.getElementsAnnotatedWith(Test.class)) {
            if (element.getKind() != ElementKind.METHOD) {
                messager.printMessage(Diagnostic.Kind.ERROR, "only methods should be annotated with Test annotation");
                continue;
            }

            var executable = (ExecutableElement) element;

            if (executable.getParameters().size() > 0) {
                messager.printMessage(Diagnostic.Kind.ERROR, "test methods doesn't accept any parameters");
            }

            if (!(executable.getReturnType() instanceof NoType)) {
                messager.printMessage(Diagnostic.Kind.ERROR, "test methods should not return anything");
            }

            if (!(executable.getModifiers().contains(Modifier.PUBLIC))) {
                messager.printMessage(Diagnostic.Kind.ERROR, "test methods should be public");
            }
        }
        return false;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return new HashSet<>(Collections.singletonList(Test.class.getName()));
    }
}
