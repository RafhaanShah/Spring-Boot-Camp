FROM gradle:6.6.1-jdk8 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM openjdk:8-jdk-alpine AS run
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar

ENV PORT=8080
HEALTHCHECK --interval=1m --timeout=10s \
  CMD wget --spider http://localhost:${PORT}/actuator/health || exit 1
ENTRYPOINT ["java", "-jar", "app/app.jar"]
