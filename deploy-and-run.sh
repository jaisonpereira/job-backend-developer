cd eureka-server/
./mvnw clean install  dockerfile:build
cd ../zuul-gateway
./mvnw clean install  dockerfile:build
cd ../login-intelipost-microservice
cd ..
docker-compose -f docker-compose-up-aplication.yml up
