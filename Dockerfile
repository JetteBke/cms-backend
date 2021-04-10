FROM openjdk:11

RUN ls -ll
RUN ls -ll | grep backend

#COPY build/libs/*.jar app.jar

#ENTRYPOINT ["java","-jar","/app.jar"]