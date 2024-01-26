# Environment
ARG JAVA_VERSION=17

FROM eclipse-temurin-${JAVA_VERSION} AS builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install

FROM eclipse-temurin-${JAVA_VERSION}
WORKDIR /app
EXPOSE 8080
COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
ENTRYPOINT ["java","-jar","/patienthubdemo.jar"]