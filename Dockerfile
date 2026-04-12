FROM gradle:8.12.1-jdk21 AS build
WORKDIR /app

COPY build.gradle.kts settings.gradle.kts /app/
COPY src /app/src

RUN gradle clean bootJar -x test

FROM ghcr.io/graalvm/graalvm-community:21.0.2
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]