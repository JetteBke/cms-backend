FROM openjdk:11

RUN ls -ll

COPY build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]