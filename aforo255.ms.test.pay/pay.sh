echo ==================================================================
echo Generate JAR 
echo ==================================================================
mvn clean package -DskipTests 
echo ==================================================================
echo BUILD 
#and PUSH Docker 
echo ==================================================================
#docker build -t antony0618/security .

echo STOP AND DELETE container======================================================
docker stop app-pay
docker rm app-pay
echo ==================================================================

echo DELETE IMAGE======================================================
docker rmi micropay
echo ==================================================================

docker build -t micropay .
#docker push antony0618/security
echo run image 
###docker run -p 8888:8888 --name config-server --network aforo255 -d config-server:v2
docker run -p 8001:8001 --name app-pay --network aforo255-test -d micropay
echo ==================================================================
echo End Process
echo =================================================================