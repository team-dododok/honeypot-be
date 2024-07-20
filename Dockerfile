# JDK 17
FROM openjdk:17

EXPOSE 8080

ARG JAR_FILE_PATH=/build/libs/*.jar
COPY ${JAR_FILE_PATH} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]