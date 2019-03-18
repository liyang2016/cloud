FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/registry.jar /app/

EXPOSE 8761

ENTRYPOINT ["java","-Xmx200m","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app/registry.jar"]
