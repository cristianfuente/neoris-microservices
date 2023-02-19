FROM openjdk:11
VOLUME /tmp
COPY target/*.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]