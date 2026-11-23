#!/usr/bin/env bash
# Compiles src/ and test/ and runs every JUnit test (Mac, Linux, Git Bash on Windows).
# Usage: bash test.sh
set -e
cd "$(dirname "$0")"
JUNIT="lib/junit-platform-console-standalone-1.11.4.jar"
rm -rf out
mkdir -p out
find src test -name "*.java" > out/sources.txt
javac -cp "$JUNIT" -d out @out/sources.txt
java -jar "$JUNIT" execute --class-path out --scan-class-path --disable-banner --details=summary
