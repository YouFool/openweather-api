FROM openjdk:8
VOLUME /tmp
COPY target/*.jar app.jar

# Exposes port 8086 by default, specify another port with -p parameter
EXPOSE 8086
ENTRYPOINT ["java","-Xms256m", "-Xmx512m","-jar","-Dspring.profiles.active=prod ","/app.jar"]