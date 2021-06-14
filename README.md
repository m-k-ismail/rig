# ReadingIsGood Java Application

ReadingIsGood is an online books retail firm which operates only on the Internet.

## Tech Stack

* Java SDK 11
* Spring Boot
* Spring Security
* JWT
* Docker
* Junit
* Mockito
* H2 DB
* JPA

## Quick Start
* Clone the repository
* Run Application.Java (System is configured to run on port 8040)
* You can find h2 console running on http://localhost:8040/h2console/
* Generate a JWT token through "authenicate" API (The token will be valid for 24 hours)
* Update your API headers to use the newly generated token
* Enjoy!
 
 You can find a full postman collection for all the APIs in resources/postman/Reading Is Good.postman_collection.json
 
 ## Design
 
 The application was designed as the following:
 1. API package contains all the APIs interfaces
 2. Each feature was wrapper in a standalone package (book, customer, order, and statistic)
 3. Inside of each of the feature package, there are mostly three packages (controller, service, and validation)
 4. Security package includes all the nessary security classes to acheive JWT authentication
 5. Exception package is responsible for handling different error responses
 6. Utils package includes some common generic utilies for the whole application
 
 ## Docker
 
 You can still run the application through docker as follows:
 1. docker build -t rig .
 2. docker run -p 8040:8040 rig
 
 ## Authentication
 
 You can use http://localhost:8040/authenticate to generate a web token and then update all of  your API headers to use the newly generated token. Header formate is: "Bearer %generated token%"
 <br>
 <br>
 username : getir
 <br>
 password: getir
 
 
