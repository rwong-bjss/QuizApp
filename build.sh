#!/bin/sh
set -e
mvn clean install
docker build -t $IMAGE .