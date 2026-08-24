#!/bin/zsh

cd "$(dirname "$0")" || exit 1

JAR_PATH="target/hello-java-web-0.0.1-SNAPSHOT.jar"

if [ ! -f "$JAR_PATH" ]; then
  echo "Jar not found: $JAR_PATH"
  echo "Run this first: mvn package"
  exit 1
fi

java -jar "$JAR_PATH"
