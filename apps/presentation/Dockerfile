FROM maven:3.8.6 AS build
WORKDIR /app
COPY pom.xml .
COPY src/ ./src/
RUN mvn package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/presentation-1.0.jar presentation.jar
CMD ["java", "-jar", "presentation.jar"]
