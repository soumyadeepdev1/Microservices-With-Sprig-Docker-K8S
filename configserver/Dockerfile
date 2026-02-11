#stage_1 : builder
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /build/root

COPY . ./

RUN mvn clean package -B  \
    -DskipTests  \
    -am



FROM eclipse-temurin:21-jre-alpine AS runner
WORKDIR /app

# Create a non-root user for security
RUN addgroup --system --gid 1001 spring && \
    adduser --system --uid 1001 spring

COPY --from=builder build/root/target/*.jar app.jar

USER spring

# Expose the application port
EXPOSE 8071

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

# Run the application
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]




