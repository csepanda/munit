package csepanda.munit.runner.services;

public interface ITestClassBuilder {
    <T> T build(Class<T> clazz) throws ReflectiveOperationException;
}
