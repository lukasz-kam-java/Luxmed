# README #


### What is this repository for? ###

* This repository contains application codebase which is recruitment task solution for the java developer position. 
* The application is based on spring-boot nad JAVA, as well as PostgreSQL
* Version 1.0
### The Task ###
1. Entity Structure: The main entity is the Company.
   * Each company has a name and includes departments.
   * Each department has a name and contains teams.
   * Each team has a name and is assigned to a project.
   *  Each project is managed by a manager, who has a name and email.
2. REST API:
   * Implement a RESTful API that supports CRUD operations for the Company entity.
   * All interactions with departments, teams, and projects should be handled through this
   single Company controller.


### How to build and run? ###

In order to build this app you need:
* JAVA 17 but it was also tested on JAVA 21
* Maven 3.6.3 or later 


#### Build and run on local host 
1. `git clone` current repository
2.  Make sure you are in the project root dir
2.  Make sure 8888 port is NOT being used by another app
3. `mvn clean package && docker-compose up`- or just first run mvn and once it completes its job run docker-compose up

####
#### Go to: http://localhost:8888/swagger-ui/index.html#/  - to see list of endpoints

Please just keep in mind that there are the same Request/Response DTOs, what should be improved
and because of that swagger shows JSON requests payloads structure that is not relevant.  


### Things that could be still done  ###
* Unit tests
* Add profiles handling 
* Add logging handling

![ERD diagram](dbErd.png)


