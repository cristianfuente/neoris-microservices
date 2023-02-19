docker-compose -f docker-compose-kafka.yml up -d
docker-compose -f docker-compose-kafka.yml exec account mvn clean install
docker-compose -f docker-compose-kafka.yml exec customer mvn clean install
docker-compose -f docker-compose-kafka.yml exec movements mvn clean install
docker-compose -f docker-compose-kafka.yml down
docker-compose -f docker-compose-services.yml up