FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/config.jar /app/
COPY ./startup.sh /usr/local/bin/

EXPOSE 8888

#ENTRYPOINT ["startup.sh","docker","config"]
