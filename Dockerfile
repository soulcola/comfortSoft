# Start with a base image containing Java runtime
FROM maven:3.8.3-openjdk-17 AS MAVEN_BUILD

# Add Maintainer Info
LABEL maintainer="8441404@gmail.com"

COPY ./ ./

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

COPY --from=MAVEN_BUILD /target/comfort-soft-1.0.jar /app.jar

# Make port 8880 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
