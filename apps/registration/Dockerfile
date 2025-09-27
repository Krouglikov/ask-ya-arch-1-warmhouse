FROM maven:3.8.6 AS build
WORKDIR /app
COPY pom.xml .
COPY src/ ./src/
RUN mvn package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/registration-1.0.jar registration.jar
CMD ["java", "-jar", "registration.jar"]
