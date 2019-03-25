FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/registry.jar /app/


EXPOSE 8761

ENTRYPOINT ["startup.sh","docker","registry"]
