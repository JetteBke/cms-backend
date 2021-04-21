FROM gradle:6.8.3-jdk11 AS builder

COPY --chown=gradle:gradle . /src
WORKDIR /src

RUN ./gradlew build --no-daemon

FROM openjdk:17-jdk-alpine3.12

COPY --from=builder /src/build/libs/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]