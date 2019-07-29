Description

This Spring boot application is to consume two rest services 

1. http://jsonplaceholder.typicode.com/users
2. http://jsonplaceholder.typicode.com/posts

Every usres have its own list of posts two different Rest service response combine in to one json response.
 
How to run this application
Right click on ApiUserControllerApplication.java as Spring boot application
	Or
Extract project
Extract project in some location on your local machine.

Build application
Go to the project location:

cd /path/to/project/location
Build project with maven by executing command:

./mvnw clean install

Start application
Execute .jar file:

java -jar target/api-user-posts-0.0.1-SNAPSHOT.jar
By default application should be accessible under localhost:8080.

There is one endpoint which returns simple jsp home page.