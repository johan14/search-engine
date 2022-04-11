#
# Build stage
#
FROM maven:3.6.0-jdk-8-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean compile assembly:single

#
# Package stage
#
FROM openjdk:8-jre-slim
COPY --from=build /home/app/target/search-engine-2-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/local/lib/search-engine.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/search-engine.jar"]