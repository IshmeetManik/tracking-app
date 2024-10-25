# tracking-app

Demo of Spring boot app for generating  unique tracking number

This is a simple spring-boot app that implement a RESTful API that generates unique tracking numbers for parcels.
## Step1 Create spring-boot-app
Create a spring-boot app with `spring-boot-starter-web`, `spring-boot-starter-data-redis`,  `lombok` dependencies.


Add the below properties to application.properties file
```
server.port=8080
spring.redis.host=redis
spring.redis.port=6379
```

 
 ## Step2 Create a Dockerfile for spring boot app
 Create a Dockerfile as shown below
 ```
FROM openjdk:17
ADD target/tracking-app-0.0.1.jar tracking-app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "tracking-app.jar"]
 ```
 
 ## Step3 Create a docker-compose file
 Create a docker-compose.yml file as shown below. Here the app is binded to redis instance


## Step 4 Execute
To execute this app<br/>
  
    mvn clean install
  
    docker compose build -up
  
Hit the following endpoint to post a record
POST http://localhost:8080/next-tracking-number
```json
{
    "tracking_number": "9ZUEFZGVLWJQZI17",
    "created_at": "2024-10-25T17:05:59.58389+05:30"
}
```

## Useful docker-compose commands
- `docker compose build -up`
- `docker compose down` (stop)
