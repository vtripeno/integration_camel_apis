FROM maven:3.5-jdk-8-alpine as build
MAINTAINER Victor Tripeno
ENV spring.profiles.active=PRD
COPY ./target /var/www
WORKDIR /var/www
ENTRYPOINT java -jar Camel-Integration-1.0.0.jar
