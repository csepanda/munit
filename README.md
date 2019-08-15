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
This module is open to extensions. However, right now it supports only non concurrent tests execution.

#### Functionality

- Tests discovery & test plan building.
- Tests execution and results capturing.
- Test report printing to specified stream.

### mUnit-launcher

This module is purposed to build executable jar file. This jar file is used for tests execution.
Right know mUnit only support non-concurrent tests running from specified jar file.

```
Usage: java -jar munit-launcher.jar [options]
  Options:
    --help, -h

    --mode, -m
      Options: [simple, concurrent]
      Default: simple
      Possible Values: [simple, concurrent]
    --source-type, -s
      Options: [jar, class, directory]
      Default: jar
      Possible Values: [jar, class, directory]
```
