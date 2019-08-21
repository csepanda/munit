#!/bin/sh

panic() { echo ERROR: "$*"; exit 2; }

mvn install || panic cannot install packages

# get executable program
cp munit-launcher/target/munit-launcher-jar-with-dependencies.jar ./munit-launcher.jar
