FROM eclipse-temurin:22

ARG JAR_FILE=target/*.jar
ARG APP_NAME=app
ARG APP_HOME=/opt/${APP_NAME}

RUN mkdir -p ${APP_HOME}

WORKDIR ${APP_HOME}

COPY ${JAR_FILE} ${APP_HOME}/application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "application.jar"]

ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"

CMD ["sh", "-c", "java ${JAVA_OPTS} -jar application.jar"]