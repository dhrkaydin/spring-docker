FROM maven:3.6.3-jdk-11 as MAVEN_BUILD
MAINTAINER Kamil
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/init-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "init-0.0.1-SNAPSHOT.jar"]
