# Assembly project

## Description
This project is a simple assembly project where users will be able to vote for an assembly and start a new one.   

This project is powered by:
- [PostgresSQL](https://www.postgresql.org/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- [Gradle](https://gradle.org/)
- [Project Lombok](https://projectlombok.org/)

## How to run
First of all, this project is written using Java version 21. Please, make sure you're using the correct java version.
```
$ java --version
```
If you're not using the correct version, please install it. You can use [SDKMAN](https://sdkman.io/) to manage your java versions.

Please, for docker, you can install the latest version of docker and docker-compose. You can follow the instructions on the official website.

Don't worry about gradle version, for this project a wrapper is configured which will be used to run all gradle commands from the CLI. 

### Running
To run the project, first you must run the docker compose file with:
```
$ docker-compose up -d
```
Please, note that removing the -d flag, the container will start and log everything on the terminal. My suggestion is to use the -d flag to ensure that the container is running on the background.
