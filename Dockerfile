FROM adoptopenjdk/openjdk13:alpine-jre

ENV FAKE_CLIENT_ID ${FAKE_CLIENT_ID}

ENV GOOGLE_CLIENT_ID ${GOOGLE_CLIENT_ID}
ENV GOOGLE_CLIENT_SECRET ${GOOGLE_CLIENT_SECRET}

ENV GITHUB_CLIENT_ID ${GITHUB_CLIENT_ID}
ENV GITHUB_CLIENT_SECRET ${GITHUB_CLIENT_ID}

ADD build/libs/dropwizard-web-template-all.jar /opt/dropwizard-web-template-all.jar
ADD config/docker-web-template.yml /opt/docker-web-template.yml

WORKDIR /opt

EXPOSE 8080

ENTRYPOINT java -jar dropwizard-web-template-all.jar server docker-web-template.yml