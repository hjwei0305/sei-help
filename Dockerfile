# Docker for java sei-help

# Base image oracle jdk8
# FROM frolvlad/alpine-java:latest
FROM openjdk:8-jre-alpine

# Author
LABEL maintainer="brianhsiung@outlook.com"

# Environment
ENV JAVA_OPTS="" APP_NAME="sei-help"

# Timezone
RUN rm -rf /etc/localtime && ln -s /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

# Application
ADD $APP_NAME-service/build/libs/$APP_NAME.jar $APP_NAME.jar

# Port
EXPOSE 8080

# Launch the application
ENTRYPOINT ["sh","-c","java -server -XX:InitialRAMPercentage=75.0  -XX:MaxRAMPercentage=75.0 -XX:+UseG1GC $JAVA_OPTS -jar $APP_NAME.jar --server.servlet.context-path=/$APP_NAME --server.port=8080"]