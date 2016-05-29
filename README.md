# Web Template

This project is a web application used to maintain a simple customer entity making use of the dropwizard framework.
It is designed to demonstrate some of the principals that should be used when constructing a web application.

It provides a web front end for managing customers as well as RESTful endpoints to perform the same operations.
Swagger is used to provide executable documentation for all of the restful endpoints, and they are tested using cucumber.

## Testing the application

In order to test the application once you have cloned the repo you should run the following:

```
gradlew acceptanceTest
```

## Running the application

In order to run the application once you have cloned the repo you should run the following:

```
gradlew run
```

Upon running this task the script will do the following (aside from the usual compilation etc.)

* Start up an embedded MySql instance on your local machine
* Use flyway to configure the embedded MySql instance
* Start up the application

Once this is complete you will be able to view the application [here](http://localhost:8090/web-template). 
This will show you the web interface that you can use to create, update and delete customers. If you want to
use the swagger documentation to explore these endpoints you can do [here](http://localhost:8090/web-template/swagger).

You should also be aware that because the application is using an embedded MySql instance, the data that you create will
not be persisted after the application is stopped. This is because it is intended to be a quick and easy demo that can be
run with minimal effort. If you wish to persist data after the application is stopped then you will need to amend the
database properties found in config/local-web-template.yml to point at your own mysql instance. Since Flyway does not
create the actual database itself so you will need to create an empty database in order for flyway to work correctly.