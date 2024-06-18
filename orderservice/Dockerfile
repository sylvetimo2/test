FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine
ENV APP_HOME=/apps/orderservice
WORKDIR $APP_HOME
COPY application.yml $APP_HOME
COPY orderservice-0.0.1-SNAPSHOT.jar $APP_HOME
CMD ["java","-jar","orderservice-0.0.1-SNAPSHOT.jar"]
