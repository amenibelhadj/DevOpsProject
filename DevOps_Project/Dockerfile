FROM openjdk:8
EXPOSE 8089
ADD target/DevOps_Project.jar DevOps_Project.jar
#RUN curl -O http://192.168.1.31:8081/repository/maven-releases/tn/esprit/DevOps_Project/2.1/DevOps_Project-2.1.jar
#CMD ["java", "-jar", "DevOps_Project-2.1"]



#ENTRYPOINT ["java","-jar","/devops.jar"]


ENTRYPOINT ["java","-jar","/DevOps_Project.jar"]

