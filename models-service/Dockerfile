FROM eclipse-temurin:21-jre

RUN apt-get update && apt-get install -y tzdata

ENV TZ=America/Guayaquil

WORKDIR /app
COPY target/*.jar app.jar

USER 1000

ENTRYPOINT ["java", \
  "-Duser.timezone=America/Guayaquil",
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-jar", "app.jar"]
