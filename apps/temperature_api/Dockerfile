FROM maven:3.8.6 AS build
WORKDIR /app
COPY pom.xml .
COPY src/ ./src/
RUN mvn package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/temperature_api-1.0.jar temperature-api.jar
CMD ["java", "-jar", "temperature-api.jar"]
