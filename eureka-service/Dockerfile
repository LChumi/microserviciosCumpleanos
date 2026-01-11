FROM eclipse-temurin:21-jre

#Instalar Curl
RUN apt-get update && apt-get install -y curl

WORKDIR /app
COPY target/*.jar app.jar

USER 1000

ENTRYPOINT ["java", \
  "-XX:+UseContainerSupport", \
  "-XX:MaxRAMPercentage=75.0", \
  "-jar", "app.jar"]