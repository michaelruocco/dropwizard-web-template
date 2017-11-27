FROM java:openjdk-8-jre-alpine

ENV FAKE_CLIENT_ID ${FAKE_CLIENT_ID}

ENV GOOGLE_CLIENT_ID ${GOOGLE_CLIENT_ID}
ENV GOOGLE_CLIENT_SECRET ${GOOGLE_CLIENT_SECRET}

ENV GITHUB_CLIENT_ID ${GITHUB_CLIENT_ID}
ENV GITHUB_CLIENT_SECRET ${GITHUB_CLIENT_ID}

ADD build/libs/dropwizard-web-template-standalone.jar /opt/dropwizard-web-template-standalone.jar
ADD config/docker-web-template.yml /opt/docker-web-template.yml

WORKDIR /opt

EXPOSE 8080

ENTRYPOINT java -jar dropwizard-web-template-standalone.jar server docker-web-template.yml