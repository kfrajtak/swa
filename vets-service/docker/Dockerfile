FROM maven:latest as builder
ENV APP_HOME=/root/dev/app/
RUN mkdir -p $APP_HOME/src
WORKDIR $APP_HOME
COPY ./src $APP_HOME/src
COPY pom.xml $APP_HOME/pom.xml
RUN mvn package

FROM openjdk:11-jre-slim-buster
# https://spring.io/guides/gs/spring-boot-docker/
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /usr/app/
COPY --from=builder /root/dev/app/target/app-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar", "/usr/app/app-0.0.1-SNAPSHOT.jar",
"--spring.config.location=/usr/app/config.properties"]