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
docker stop app-transaction
docker rm app-transaction
echo ==================================================================

echo DELETE IMAGE======================================================
docker rmi microtransaction
echo ==================================================================

docker build -t microtransaction .
#docker push antony0618/security
echo run image 
###docker run -p 8888:8888 --name config-server --network aforo255 -d config-server:v2
docker run -p 8003:8003 --name app-transaction --network aforo255-test -d microtransaction
echo ==================================================================
echo End Process
echo =================================================================