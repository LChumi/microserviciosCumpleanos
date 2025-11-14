FROM eclipse-temurin:21-jdk

ARG JAR_FILE=target/*.jar

#Usuario no root con UID fijo
RUN useradd -m -u 1000 appuser \
    && mkdir -p /app \
    && chown -R appuser:appuser /app

# Copiar jar
COPY ${JAR_FILE} /app/app.jar
RUN chown appuser:appuser /app/app.jar

USER appuser

ENTRYPOINT ["java", "-jar", "/app/app.jar"]