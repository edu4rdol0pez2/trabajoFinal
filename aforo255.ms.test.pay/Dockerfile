FROM openjdk:11
EXPOSE 8001
##EXPOSE 80
ADD ./target/aforo255.ms.test.pay-0.0.1-SNAPSHOT.jar ms-pay.jar
ENTRYPOINT ["java","-jar","/ms-pay.jar"]
##ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/ms-historical.jar"]