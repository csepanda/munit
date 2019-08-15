# mUnit

This project is tesk task for Java developer position.
So if you accidently find this repository please be aware of this fact.

mUnit is 'mini JUnit' implementation which provide `@Test` annotation and tests lancher.

## Modules

This project consist of three modules which are described below.

### mUnit-core

Core module that contains required `@Test` annotation and its processor which prevent illegal usage.
This module is purposed to have essential for mUnit usage is test code writting.
In possibly next 'releases' it might contain assertion and assumptions methods, `@setUp` `@tearDown` and other useful annotations.

#### Functionality

Right now core module only provides `@Test` annotation.
`@Test` can be only applied to public methods without arguments and with void return type.

### mUnit-runner

Runner is module which contain functionality for programmable test running.

#### Functionality

- Tests discovery & test plan building.
- Tests execution and results capturing.
- Test report printing to specified stream.

### mUnit-launcher

This module is purposed to build executable jar file. This jar file is used for tests execution.
Right know mUnit only support non-concurrent & concurrent tests running from specified jar file.

```
Usage: java -jar munit-launcher.jar [options]
  Options:
    --help, -h

    --mode, -m
      Options: [simple, concurrent]
      Default: simple
      Possible Values: [simple, concurrent]
    --source-type, -s
      Options: [jar]
      Default: jar
      Possible Values: [jar]
```

## Examples of usage

Currently there are two example projects:
- One that shows @Test annotation application on class members.
- One with success, failed and "not run" method.

## Illegal annotation usage

**Prerequisites**

You need to install munit-core.
```
$ ./install.sh
```

**Steps**

1. Change directory to the examples/illegal-annotation-usage
2. Try to compile the project `mvn compile`

**Expected result**

Compilation will fail with message that state illegal usage of annotations.
```
[ERROR] error: test methods should be public
[ERROR] error: test methods should be public
[ERROR] error: test methods should be public
[ERROR] error: test methods should not return anything
[ERROR] error: test methods doesn't accept any parameters
```


### Tests

**Prerequisites**

You need to install munit-core and get executable munit-launcher.

```
$ ./install.sh
```

**Steps**

1. Change directory to the examples/tests.
2. Build package with tests: `mvn package`.
3. Return to the project root direcotry.
3. Execute munit-launcher with enabled assertions: `java -jar -ea munit-launcher.jar examples/tests/target/tests.jar`

**Expected result**

Tests will run and report will be printed to stdout.

Report:
```
TestsInheritance - derivedClassMethod SUCCESS
TestsInheritance - superFooTest SUCCESS
TestsInheritance - superBarTest SUCCESS
SuperTests - superFooTest SUCCESS
SuperTests - superBarTest SUCCESS
AbstractNotRunned - testInAsbtractClass NOT_RUNNED
FailedTests - testWithException FAILED
FailedTests - testWithNativeAssert FAILED
SimpleTests - fooTest SUCCESS
[SUMMARY] success: 6, failed: 2, notRun: 1
```
