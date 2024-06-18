FROM adoptopenjdk/openjdk11:jre-11.0.9.1_1-alpine
ENV APP_HOME=/apps/gateway
WORKDIR $APP_HOME
COPY application.yml $APP_HOME
COPY api-gateway-0.0.1-SNAPSHOT.jar $APP_HOME
CMD ["java","-jar","api-gateway-0.0.1-SNAPSHOT.jar"]
