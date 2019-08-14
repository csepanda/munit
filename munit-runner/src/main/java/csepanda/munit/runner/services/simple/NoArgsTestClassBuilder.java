package csepanda.munit.runner.services.simple;

import csepanda.munit.runner.services.ITestClassBuilder;

public class NoArgsTestClassBuilder implements ITestClassBuilder {

    @Override
    public <T> T build(Class<T> clazz) throws ReflectiveOperationException {
        var constructor = clazz.getConstructor();

        return constructor.newInstance();
    }
}
