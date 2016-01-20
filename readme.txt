Steps to run:

Required Softwares:
* java-8-jdk/jre
* maven
* tomcat server
* mysql server

1) Assuming you have a MySQL user acccount server setup, Edit the src/main/resources/db.properties with the right details of the MySQL account.
2) Login to the MySQL account and execute schema.sql located in the project's root directory. This file has the schema of the database.
3) To package, run: 'mvn clean package'. This generates a war file in the target folder. The war file name is feedgenerator.war.
4) Copy this file to the webapps directory in present in the Tomcat installation folder. Start the server.

5) Check catalina.out for any errors.
6) To check if the app is up, run: curl http://localhost:8080/feedgenerator/ping. This response should be "All is Well!" (Replace localhost with the host name of the machine where the application is required)

REST APIs;

There are three main resources in this webapp: Users, Topics, UserRoles. The REST APIs for all the resources follow the same convention. Below are the only things that you need to remember when using the APIs. (The structure of the request and reponse for each endpoint is different, obviously).

Operation: INSERT/CREATE
HTTP verb: POST
Resource endpoint: <BASEURI>/users,<BASEURI>/userroles, <BASEURI>/topics

Operation: UPDATE
HTTP verb: PUT 
Resource endpoint: <BASEURI>/users,<BASEURI>/userroles, <BASEURI>/topics
 
Operation: DELETE
HTTP verb: DELETE
Resource endpoint: <BASEURI>/users/{id}, <BASEURI>/userroles/{id}, <BASEURI>/topics/{id}

Operation: GET
HTTP verb: GET
Resource endpoint: <BASEURI>/users/{id}, <BASEURI>/userroles/{id}, <BASEURI>/topics/{id}

Operation: GETALL
HTTP verb: GET
Resource endpoint: <BASEURI>/users, <BASEURI>/userroles, <BASEURI>/topics


JSON Structures:

#########
Create a User:
{
  "username": "test3",
  "email": "email",
  "firstName": "fn",
  "lastName": "ln",
  "roles": [
    "admin",
    "subscriber"
  ],
  "topics": {
    "topic1": 0.5,
    "topic2": 0.5
  }
}

Update a User:
{
  "id": 1,
  "username": "test3",
  "email": "email",
  "firstName": "fn",
  "lastName": "ln",
  "roles": [
    "admin",
    "subscriber"
  ],
  "topics": {
    "topic1": 0.5,
    "topic2": 0.5
  }
}
GOTCHAs for update: the existing roles during update don't get updated. New Roles will be added if they are not already there. Topics: the existing topics will be cleared and the new topics would be added with the latest weights(This is very inefficient. Having explicit fields which indicates the topics added/removed/modified would be a better appraoch. However, the JSON structure would be slightly complex)


Get a User: The response is similar the Create request. But it will have an id field.

#########
Create a UserRole:
{
  "name": "newrole"
}

Get a UserRole: The response is similar the Create request. But it will have an id field.

#########
Create a Topic:
{
  "name": "topic1"
}

Get a Topic: The response is similar the Create request. But it will have an id field.


