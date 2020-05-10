FROM openjdk:11-jre-slim

LABEL maintainer="Subhrodip Mohanta"
LABEL email="hello@subho.xyz"
LABEL application="Coronavirus Tracker Application"

COPY covid-tracker/target/covid-tracker-1.1.jar /usr/local/covid-tracker/

EXPOSE 8080

CMD ["java", "-jar", "/usr/local/covid-tracker/covid-tracker-1.1.jar"]
