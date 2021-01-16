FROM openjdk:11
EXPOSE 8003
##EXPOSE 80
ADD ./target/aforo255.ms.test.transaction-0.0.1-SNAPSHOT.jar ms-transaction.jar
ENTRYPOINT ["java","-jar","/ms-transaction.jar"]
##ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","/ms-historical.jar"]