FROM openjdk:8
VOLUME /tmp
COPY target/*.jar app.jar

EXPOSE 8086
ENTRYPOINT ["java","-Xms256m", "-Xmx512m","-jar","-Dspring.profiles.active=prod ","/app.jar"]