package csepanda.munit.launcher.loaders;

public interface ILoader {
    Iterable<Class<?>> load() throws ClassNotFoundException;
}
