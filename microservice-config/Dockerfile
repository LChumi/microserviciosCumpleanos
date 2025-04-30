FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
VOLUME /tmp
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["jar", "-jar", "/app.jar"]