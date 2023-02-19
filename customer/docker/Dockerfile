FROM openjdk:11
VOLUME /tmp
COPY target/customer-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]