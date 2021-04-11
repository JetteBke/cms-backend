# Contact Organizer Backend

This is the backend for my small contact organizer application.

## Prerequisites

You need to have `docker`, `docker compose` and `postgres` installed.

## Run locally

Run `docker-compose up` in the root directory. (currently not working)

(This is a workaround until I figure out how to create db in a different way):

First you need to start a database with

```
docker run -p 5432:5432 -e POSTGRES_PASSWORD=password postgres
```

Then connect to it via

```
psql -U postgres -h localhost postgres
```

Create a database named `cms` with 
```aidl
Create database cms;
```
After that you can run the app with `./gradlew bootRun` from the root directory.

There is some fake data so to try if the app is working properly you can call `localhost:8080/hello` and it should return a list with two entries.

____________________

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/html/#build-image)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

