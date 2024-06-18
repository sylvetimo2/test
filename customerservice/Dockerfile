FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine
ENV APP_HOME=/apps/customerservice
WORKDIR $APP_HOME
COPY application.yml $APP_HOME
COPY customerservice-0.0.1-SNAPSHOT.jar $APP_HOME
CMD ["java","-jar","customerservice-0.0.1-SNAPSHOT.jar"]
