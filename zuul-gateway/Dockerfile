FROM openjdk:8-jdk
VOLUME /tmp
ARG JAR_APP
COPY ${JAR_APP} aplication.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/aplication.jar"]