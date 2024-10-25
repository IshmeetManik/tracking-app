FROM openjdk:17
ADD target/tracking-app-0.0.1.jar tracking-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tracking-app.jar"]