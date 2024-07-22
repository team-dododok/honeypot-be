# JDK 17
FROM openjdk:17

EXPOSE 8080

COPY /build/libs/honeypot-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]