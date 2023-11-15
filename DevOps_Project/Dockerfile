FROM openjdk:11
EXPOSE 8089
#ADD target/gestion-stock-ski-1.0.jar gestion-stock-ski-1.0.jar
RUN curl -O http://192.168.100.232:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar
ENTRYPOINT ["java","-jar","/DevOps_Project-2.1.jar"]
