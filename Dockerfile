FROM openjdk:8-alpine

COPY target/*.jar /app.jar

EXPOSE 8086
ENTRYPOINT ["java", "-Xms256m", "-Xmx512m", "-jar", "/app.jar"]