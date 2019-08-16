# Spring Cloud - Login Storm System

### Current scenario analysis

>  1- Imagine that we have a login system and user profiles today. The system has more than 10 million users, and we have a concurrent access of about 5,000 users. Today the system home screen is very slow. In this screen is made a query in the database to get the user information and display it in a personalized way. When there is a spike in simultaneous logins, loading of this screen becomes too slow. In your view, how could we begin the search for the problem, and what kind of improvement could be made?
>  
Answer: We will start searching for problems through CPU / Memory usage metrics using Jmeter, found that we would go to the Database communication for see, query metrics tools like Hibernate Statistics (if using JPA) can help us determine points of system improvement.
Because it is an application that receives many concurrent accesses at certain times, adopting a scalable architecture is the most viable solution. After the development of the scalable login base structure we can host a service that has an automatic auto-scalling, decreasing the moments. low-cost, enabling the cost
Points with a huge access frequency can be elevated to a second level cache.


> 2 - Based on the previous issue, we would like you to code a new login system for many simultaneous users and home screen loading. Remember that it is a web system so we will have static and dynamic content. Also take into consideration that in the company there is another system that will also request user data, so this system must expose the information to this other system in some way.

The solution made possible to login with a high usage load was to develop a system that can scale as needed.

#### A Stack - Spring Cloud + Netflix OSS:

* #### Eureka Server - Netflix API
The word eureka was supposedly pronounced by the scientist
Archimedes (287 BC - 212 BC) when he discovered how
solve a complex dilemma. In our context, it looks for all Spring Cloud services and allows them to register with it
to become available for use.

* #### Zuul Gateway - Netflix API
In the 1984 Ghostbuster movie, Zuul was the doorman of the
portal of Gozer, an ancient god who wanted to bring all the
demons from your universe to the city of New York. In our
context, he who will receive all requests and send to
available servers, consulted the eureka server

* ####  Login-intelipost-microservice-api

Service designed to receive each request in a distributed way, stateless service facilitates scheduling of it by not saving state.

Stack

* Spring boot
* Docker
* Apache Maven
* Angular JS
* JWT
* Spring Data JPA - Hibernate
* Spring Data + Redis Cache

* #### Postgres User Query Strategy Evolution

At first the solution adopted was to query using Hibernate Stateless Session (A stateless session that does not implement a hibernate first level cache or interact with any hibernate second level cache)
but because it is a system with a user base in the thousands
the ideal solution found by a benchmark with hibernate statistics
was using jdbc, regardless of the strategy used with hibernate the number of simultaneous access degrades performance exponentially.
In order to improve the performance of postgres accesses, the caching strategy was implemented using spring-cache + spring-data-redis.


* Authentication Strategy

For authentication and authorization was used + Spring-Security-JWT using specification RFC 7519 (https://jwt.io/) - Facilitating scalability and eliminating the need to exchange cookies between applications and browsers or use strategies such as distributed session, Session Migration, Sticky Session (Aws),
We take an approach that benefits us from scalability that is compatible with most applications (Especially mobile devices) on the market. In a future scenario secret-token and expiration-time can be shared using the config server. this way we can change the authentication strategy without affecting the runtime application
In general we may not worry about Cross-Site Request Forgery (CSRF) protection.
), as we will be working with a stateless system.

* To make logged in users available we persist the history of tokens used, login email, authentication date and token expiration time using spring-data-redis, so microservices can consult the logged in user history using the application. the expiration date criterion available at the time of token generation in addition to providing a way to query logged in users (using the token timeout criterion) we will get data that can be used to formalize usage statistics as frequency metrics and etc.
If the load-balancer drops and the user is redirected to a geographically distinct server that has not yet received data distribution between the clusters and the user's JWT is still valid there is no session drop.


### Build and Deploy 
Requirements Git , Linux,Java 8,docker/docker-compose,JAVA_HOME

1. Clone this repository
2.  give permission for the script if necessary 
sudo chmod +x deploy-and-run.sh
3. execute the script 
./deploy-and-run.sh

4. Access the login http://localhost:8080/

5. View the availables serves http://localhost:8761/eureka

> * Observation:
> - Wait the finish “register-server” by service execution (Heart Beat).
> - docker needed permission to run without sudo
> - Clone in the folder doesnt need more permission by default 

Users available

> user: jaison@intelipost
> pass: recrutado

> user: admin@admin
> pass: admin