#CDP Module JMP7.6: Backend: Web Services  

1. REST:

Implement backend for User service and develop REST architecture.

You have next entities: User(id, name, surname, mail, tasks), Task(id, name, description, creationDate, deadLine).

App should support CRUD operations for mentioned entities.

Operation should be defined by HTTP methods.

You should follow resources approaches like: user/%userId/task/%taskId

Test your app with any REST cleint: java client, IDEA, soapUI, curl, Postman,

## To run it:
 - `mvn clean install`
 - `java -jar target/module6-0.0.1-SNAPSHOT.jar com.epam.brest.jmp.app.TaskManagerApplication`
 - access it on `http://localhost:8080/services/app/user/{#}/task/{#} to get a specific task` 

2. SOAP:

Creare corresponding web services for mentioned CRUD operations for your entities. 

Generate WSDL and publish.

Generate client and verify your web services.


## To run an application
Refer to [Main Readme](../README.md)