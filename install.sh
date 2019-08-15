#!/bin/sh

panic() { echo ERROR: "$*"; exit 2; }

mvn package || panic cannot build packages

# install core module
# `mvn install -pl core` fails to execute due to the some depedencies
cd munit-core && mvn install && cd ..

# get executable program
cp munit-launcher/target/munit-launcher-jar-with-dependencies.jar ./munit-launcher.jar
