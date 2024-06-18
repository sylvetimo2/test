FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine
ENV APP_HOME=/apps/bookservice
WORKDIR $APP_HOME
COPY application.yml $APP_HOME
COPY bookservice-0.0.1-SNAPSHOT.jar $APP_HOME
CMD ["java","-jar","bookservice-0.0.1-SNAPSHOT.jar"]
