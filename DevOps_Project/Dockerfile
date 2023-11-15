FROM openjdk:8
EXPOSE 8089
#ADD target/DevOps_Project-2.1.jar DevOps_Project-2.1.jar
RUN curl -O http://192.168.1.31:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar
ENTRYPOINT ["java","-jar","/devops.jar"]


#ENTRYPOINT ["java","-jar","/supplier-devops-1.0.jar"]

