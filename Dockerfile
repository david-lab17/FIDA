FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
EXPOSE 8080
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]