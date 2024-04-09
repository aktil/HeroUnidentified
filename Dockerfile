FROM ubuntu:latest
LABEL authors="Akylbek"
RUN apt update &&  \
    apt upgrade -y &&  \
    apt-get install -y openjdk-17-jdk &&  \
    apt-get install xvfb && \
    export DISPLAY=:0 && \
    Xvfb :0 -screen 0 1024x768x24 &
WORKDIR /usr/src/app
ARG JAR_FILE=target/*.jar
COPY ./target/Tes1.jar test.jar
ENTRYPOINT ["java", "-jar", "test.jar"]