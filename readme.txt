Instruction to Run the application:
1. Clone the project Repository  to local Folder
https://github.com/sylvetimo2/test/

To build the application using maven 
 1. navigate to each subproject folder and issue command :
 2. Edit the application.yml to point to your local mysqlDatabase;
 3. Create database named bookstore on your local Mysql Instance
 4. Run command  mvn install  
    To  build the application 
To Run the application  in predefined order  run command 
  docker compose up --force-recreate --build -d

To access below endpoints for documentation 
  Book Service
 http://localhost:8081/api-docs/swagger-ui/index.html
  Customer Service
 http://localhost:8082/api-docs/swagger-ui/index.html
   Order Service 
 http://localhost:8083/api-docs/swagger-ui/index.html
 
 Service Registry on Eureka 
  http://localhost:8761/
 
Token Service for auth2
  http://localhost:8080/.well-known/openid-configuration
  
  
Use The Postman collection to Test The service endpoints . 
 