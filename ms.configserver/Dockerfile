FROM openjdk:13 
EXPOSE 8888 
ADD ./target/ms.configserver-0.0.1-SNAPSHOT.jar   config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]

