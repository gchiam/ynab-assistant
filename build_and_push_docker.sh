#!/bin/bash

set -e
echo "Building ${IMAGE:=gchiam/ynab-assistant}:${TAG:=latest}..."

echo "Building binary..."
./gradlew installDist

echo "Building docker image..."
docker build -t ${IMAGE}:${TAG} .

echo "Pushing docker image to dockerhub..."
docker push ${IMAGE}:${TAG}
