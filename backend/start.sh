#!/bin/bash

echo "Build Backend Service Project"
cd health-service/
./gradlew clean build
cd ..

echo "Build Consumer Project"
cd health-consumer/
./gradlew clean build
cd ..

echo "Start Backend Project"
docker-compose -f docker-compose.yml build
docker-compose -f docker-compose.yml up