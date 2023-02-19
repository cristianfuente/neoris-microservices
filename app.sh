#!/bin/bash

cd account
docker-compose up -d
sh ./mvnw clean install
docker-compose down

cd ../customer
sh ./mvnw clean install

cd ../movements
sh ./mvnw clean install

cd ..
docker-compose up -d