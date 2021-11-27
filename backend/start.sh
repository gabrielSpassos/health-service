#!/bin/bash

echo "Build Backend Project"
./gradlew clean build

echo "Start Backend Project"
docker-compose -f docker-compose.yml build
docker-compose -f docker-compose.yml up