FROM java:openjdk-8-jre-alpine

ENV APP_AUTH_TYPE fake
ENV APP_GOOGLE_CLIENT_ID setthis
ENV APP_GOOGLE_CLIENT_SECRET setthis

ADD build/libs/dropwizard-web-template-standalone.jar /opt/dropwizard-web-template-standalone.jar
ADD config/docker-web-template.yml /opt/docker-web-template.yml

WORKDIR /opt

EXPOSE 8080

ENTRYPOINT java -jar dropwizard-web-template-standalone.jar server docker-web-template.yml