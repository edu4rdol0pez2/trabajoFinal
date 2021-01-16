echo ==================================================================
echo Generate JAR 
echo ==================================================================
mvn clean package -DskipTests 
echo ==================================================================
echo BUILD and PUSH Docker 
echo ==================================================================
docker build -t antony0618/security .
docker push antony0618/security
echo run image 
###docker run -p 8888:8888 --name instance-app-configserver --network aforo255-test -d config-server:v2
echo ==================================================================
echo End Process
echo =================================================================