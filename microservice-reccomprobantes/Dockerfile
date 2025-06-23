FROM openjdk:17-jdk-slim

#Crear usuario no-root con UID fijo
RUN useradd -m -u 1000 appuser \
    && mkdir -p /app/data/clientes \
    && chown -R appuser:appuser /app

#Copiar el Jar
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

RUN chown appuser:appuser /app/app.jar

USER appuser

ENTRYPOINT ["java", "-jar", "/app.jar"]