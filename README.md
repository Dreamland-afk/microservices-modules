 Create the docker image of each module using 
 
 mvn spring-boot:build-image 
 
 command.

 docker run -it --rm -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management

netstat -ano | findstr :8090

taskkill /PID 11144 /F
