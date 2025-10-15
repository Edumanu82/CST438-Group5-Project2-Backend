# ---- Build stage ----
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Cache dependencies first (better build times)
COPY gradlew gradlew
COPY gradle gradle
COPY build.gradle settings.gradle ./
RUN chmod +x gradlew
RUN ./gradlew --no-daemon dependencies || true

# Now copy sources and build the jar
COPY src src
RUN ./gradlew --no-daemon clean bootJar

# ---- Run stage ----
FROM eclipse-temurin:21-jre

WORKDIR /app
# Copy the fat jar
COPY --from=build /app/build/libs/*-SNAPSHOT.jar /app/app.jar

# Healthcheck (optional)
HEALTHCHECK --interval=30s --timeout=3s --start-period=20s --retries=3 \
CMD wget -qO- http://localhost:8080/actuator/health | grep -q '"status":"UP"' || exit 1

# JVM flags toggleable via JAVA_OPTS
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75 -Djava.security.egd=file:/dev/./urandom"

EXPOSE 8080
ENTRYPOINT ["sh","-c","java $JAVA_OPTS -jar /app/app.jar"]
