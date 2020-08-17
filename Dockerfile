FROM openjdk:11-jre-slim

LABEL maintainer="Subhrodip Mohanta"
LABEL email="hello@subho.xyz"
LABEL application="Coronavirus Tracker Application"

ARG JAR_FILE=covid-tracker/target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "/app.jar" ]
