FROM adoptopenjdk/openjdk11:alpine
MAINTAINER fernando
COPY target/api-operating-0.0.1-SNAPSHOT.jar  api-operating-0.0.1-SNAPSHOT.jar.jar
ENTRYPOINT ["java","-jar","/api-operating-0.0.1-SNAPSHOT.jar"]