FROM openjdk:8-jdk
ADD build/libs/dropwizard-web-template-all.jar /opt/dropwizard-web-template-all.jar
ADD config/docker-web-template.yml /opt/docker-web-template.yml
WORKDIR /opt
EXPOSE 80
ENTRYPOINT java -jar dropwizard-web-template-all.jar server docker-web-template.yml