# NOTE: This Dockerfile is auto generated and filtered via maven resources plugin.
# Edits to generated Dockerfile will NOT be saved.
# Only changes made in docker/Dockerfile template file will be persistent

FROM adoptopenjdk/openjdk11:alpine-jre

MAINTAINER Rajiv Singla <rs153v@att.com>

ENV SPRING_PROFILES_ACTIVE local
ENV SERVER_PORT 8091
ENV LOG_CONFIG_FILE logback-spring.xml
ENV LOG_FILE logs/${project.artifactId}.log

# INSTALL UTILS
RUN apk update && \
    apk --no-cache add gzip && \
    apk --no-cache add curl && \
    apk --no-cache add openssh && \
    apk --no-cache add bind-tools && \
    apk --no-cache add htop

# ADD APP JARS
VOLUME /tmp
ADD docker/${LOG_CONFIG_FILE} ${LOG_CONFIG_FILE}
ADD target/${project.build.finalName}.jar ${project.artifactId}.jar

# EXPOSE APP PORT
EXPOSE ${SERVER_PORT}

ENTRYPOINT exec java ${JAVA_OPTS} \
            "-Djava.security.egd=file:/dev/./urandom"  \
            "-Dlogging.config=${LOG_CONFIG_FILE}"  \
            "-Dlogging.file=${LOG_FILE}"  \
            "-jar"  "${project.artifactId}.jar"  \
            "--spring.profiles.active=${SPRING_PROFILES_ACTIVE}"

