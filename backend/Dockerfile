FROM openjdk:8-jdk-alpine
ARG JAR_FILE=./target/*.jar
ARG APP_DIR=/opt/app

# ENV DB_URL=my-sql
# ENV DB_USER=root
# ENV DB_PASS=0000

COPY ${JAR_FILE} ${APP_DIR}/application.jar
WORKDIR ${APP_DIR}
# EXPOSE 8081
ENTRYPOINT ["java", "-jar", "application.jar"]