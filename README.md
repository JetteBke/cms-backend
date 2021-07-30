# Contact Organizer Backend

This is the backend for my small contact organizer application.

## Prerequisites

You need to have `docker`, (`docker compose`) and `postgres` installed.

## Run locally

When running it the first time you need to create the database manually: 

First you need to start a database with

```
docker run -p 5432:5432 -e POSTGRES_PASSWORD=<YOUR_PASSWORD> <YOUR_DB_NAME>
```

Then connect to it via

```
psql -U postgres -h localhost postgres
```

Create a database with a name of your choice
```aidl
Create database <YOUR_DB_NAME>;
```

After that you need to make sure that you also have the needed [frontend repository](https://github.com/JetteBke/contact-organizer-for-fun) cloned.
For both, this one and the frontend repo you need to build the docker images with
```aidl
docker build -t <THE_IMAGE_NAME_YOU_SELECT>:<THE_VERSION_TAG_YOU_WANT> .
```
Alternatively you can pull the latest version of the frontend image from [dockerhub](https://hub.docker.com/repository/docker/jettebke/contact-management/tags?page=1&ordering=last_updated).

Make sure to put the correct image names in the `docker-compose.yml` file in the root directory of this repository. 
Also add there your db credentials in order to connect the application to the correct db.

Run `docker compose up` in the root directory. (This should now start the database, frontend and backend and you should be able to access the application at `http://localhost:3000`)

Whenever you are done, just run `docker compose down` to stop the different services. 
____________________

### Reference Documentation

For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/html/#build-image)

### Additional Links

These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

