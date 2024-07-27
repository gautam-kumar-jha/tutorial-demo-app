FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests -X

FROM openjdk:17-alpine
VOLUME /tmp
COPY --from=builder /app/target/tutorial-demo-app-0.0.1.jar tutorial.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/tutorial.jar"]
