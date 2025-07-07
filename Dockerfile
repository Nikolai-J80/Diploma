FROM openjdk:17-jdk-alpine

EXPOSE 9090

COPY target/CloudStorageApplication-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT  ["java", "-jar", "app.jar"]