FROM openjdk:8
EXPOSE 8080
ADD target/translator-docker.jar translator-docker.jar
ENTRYPOINT ["java","-jar","translator-docker.jar"]