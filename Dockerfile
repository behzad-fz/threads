FROM gradle:8.2.1-jdk8 AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM openjdk:8

WORKDIR /home/gradle/src

COPY --from=build /home/gradle/src/build/libs/threads-1.0-SNAPSHOT.jar /app/threads.jar

CMD ["java", "-jar", "/app/threads.jar"]
