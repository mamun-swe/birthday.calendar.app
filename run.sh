#!/bin/sh

rm -f *.jar
./mvnw clean install -DskipTests
cp target/*.jar runnable.jar

docker-compose down
docker-compose build
docker-compose up -d

echo "App Compiled Successfully"
echo "Application running on http://localhost:8090/"
echo "phpmyadmin running on http://localhost:8080/"