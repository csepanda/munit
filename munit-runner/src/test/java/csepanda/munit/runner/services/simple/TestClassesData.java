package csepanda.munit.runner.services.simple;

public class TestClassesData {
    public class FooAndBarMethods {
        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void foo() { }

        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void bar() { }
    }

    public class SingleFooMethod {
        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void foo() { }
    }

    public class SingleBarMethod {
        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void bar() { }
    }

    public class DerivedWithOverloadClass extends BaseClass {
        @Override
        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void foo() {
        }

        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void bar() {
        }
    }

    public class BaseClass {
        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void foo() {
        }

        @SuppressWarnings({"unused", "EmptyMethod"})
        @csepanda.munit.annotation.Test
        public void foobar() {
        }
    }
}

