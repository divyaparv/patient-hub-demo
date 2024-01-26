# Environment
ARG JAVA_VERSION=17

FROM eclipse-temurin:21.0.2_13-jre-ubi9-minimal
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]