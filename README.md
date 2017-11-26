# Web Template

[![Build Status](https://travis-ci.org/michaelruocco/dropwizard-web-template.svg?branch=master)](https://travis-ci.org/michaelruocco/dropwizard-web-template)
[![Coverage Status](https://coveralls.io/repos/github/michaelruocco/dropwizard-web-template/badge.svg?branch=master)](https://coveralls.io/github/michaelruocco/dropwizard-web-template?branch=master)
[![Dependency Status](https://www.versioneye.com/user/projects/574ac6f5ce8d0e0047373380/badge.svg?style=flat)](https://www.versioneye.com/user/projects/574ac6f5ce8d0e0047373380)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/66aa2b36d38d4e28a1c9440fce23266f)](https://www.codacy.com/app/michael-ruocco/dropwizard-web-template?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/dropwizard-web-template&amp;utm_campaign=Badge_Grade)

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

Once this is complete you will be able to view the application [here](http://localhost:8090/). 
This will show you the web interface that you can use to create, update and delete customers. If you want to
use the swagger documentation to explore these endpoints you can do [here](http://localhost:8090/swagger).

You should also be aware that because the application is using an embedded MySql instance, the data that you create will
not be persisted after the application is stopped. This is because it is intended to be a quick and easy demo that can be
run with minimal effort. If you wish to persist data after the application is stopped then you will need to amend the
database properties found in config/local-web-template.yml to point at your own mysql instance. Since Flyway does not
create the actual database itself so you will need to create an empty database in order for flyway to work correctly.

## Docker

The application is already built into a docker image that is host on [docker hub](https://hub.docker.com/r/michaelruocco/web-template/),
but if you want to tweak the application or build your own container there is a Dockerfile within the
repo, to build it you can run the following command:

```
docker build -t <your_tag> .
```

The application can also be run using docker, if you have docker-compose installed on your
machine you can run the application using the following commands:

```
docker-compose up app
```

This will fire up docker containers, a mysql database, a container that will run the flyway
migrations against that database and a third container that will run the app. Once all the containers
have started up you can access the application here: Once this is complete you will be able to view
the application [here](http://localhost:8090/).

The application will run with a fake implementation of single sign on by default. If you are
running from gradle, you can enabled google SSO by providing three system properties as shown below:

```
gradlew run -Papp.auth.type=google -Papp.google.client.id=<YOUR_CLIENT_ID> -Papp.google.client.secret=<YOUR_CLIENT_SECRET>
```

You can also enabled google SSO when running through docker compose, to do this you will need to
create a .env file in the root of the project and set values for three environment variables as shown
below:

```
APP_AUTH_TYPE=google
APP_GOOGLE_CLIENT_ID=<YOUR_CLIENT_ID>
APP_GOOGLE_CLIENT_SECRET=<YOUR_CLIENT_SECRET>
```