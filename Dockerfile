FROM gradle:6.6.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM openjdk:8-jdk-alpine AS run
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
EXPOSE 8080/tcp
HEALTHCHECK --interval=30s --timeout=3s \
  CMD curl -f http://localhost/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "app/app.jar"]
