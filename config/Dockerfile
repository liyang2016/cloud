FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/config.jar /app/
COPY ./startup.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/startup.sh
RUN chmod -x /app/*.jar

EXPOSE 8888

ENTRYPOINT ["startup.sh","docker","config"]