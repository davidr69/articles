# build stage
ARG APP_VERSION="3.4.10"

FROM docker.io/gradle:9-jdk25 AS builder
ARG APP_VERSION
WORKDIR /build
COPY . .
RUN gradle build -x test

# Runtime stage
FROM registry:5000/awscorretto:25
ARG APP_VERSION="3.4.10"
COPY --from=builder /build/build/libs/articles-${APP_VERSION}.jar /app/articles.jar
WORKDIR /app
USER nobody
ENTRYPOINT ["/bin/sh", "-c", "java -jar articles.jar"]
