FROM --platform=linux/amd64 openjdk:21-jdk

ENV SPRING_PROFILES_ACTIVE=default

COPY build/libs/assembly-0.0.1-SNAPSHOT.jar assembly-app.jar


EXPOSE 8080

ENTRYPOINT ["java", "-jar", "assembly-app.jar"]