# Warehouse manager #
Warehouse manager is simple application which helps manage warehouse. 

Application is still under development. 

### Build With ###
* Java - main programming language
* Spring Boot, Spring MVC, Spring WEB, Hibernate - frameworks
* Gradle- dependency management

### Prerequesites ###
* Gradle

### How to run ###

1. Download project from github.
2. Create database in mysql using "create database warehouse_manager" query.
3. Update: 'spring.datasource.username' and 'spring.datasource.password' in application.properties file
4. Create tables base on queries provided in db_scheme.sql file.
5. Build project using 'gradlew build' command. 
6. Run the application. You can test application using f.e. Postman. 
