#!/bin/bash

echo "Build Backend Project"
cd backend/health-service/
./gradlew clean build

echo "Start Backend Project"
docker-compose build
docker-compose up