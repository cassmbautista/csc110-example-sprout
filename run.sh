#!/usr/bin/env bash
# Compiles everything in src/ and launches the app (Mac, Linux, Git Bash on Windows).
# Usage: bash run.sh
set -e
cd "$(dirname "$0")"
rm -rf out
mkdir -p out
find src -name "*.java" > out/sources.txt
javac -d out @out/sources.txt
java -cp out Main
