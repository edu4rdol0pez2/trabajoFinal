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
docker stop app-invoices
docker rm app-invoices
echo ==================================================================

echo DELETE IMAGE======================================================
docker rmi microinvoices
echo ==================================================================

docker build -t microinvoices .
#docker push antony0618/security
echo run image 
###docker run -p 8888:8888 --name config-server --network aforo255 -d config-server:v2
docker run -p 8002:8002 --name app-invoices --network aforo255-test -d microinvoices
echo ==================================================================
echo End Process
echo =================================================================