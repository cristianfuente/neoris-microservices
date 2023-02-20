#!/bin/bash
docker compose -f docker-compose-kafka.yml up -d
cd account
sh ./mvnw clean install
cd ../customer
sh ./mvnw clean install
cd ../movements
sh ./mvnw clean install


cd ..
docker compose -f docker-compose-kafka.yml down
docker compose -f docker-compose-services.yml up