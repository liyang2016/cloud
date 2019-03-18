FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/gateway.jar /app/

EXPOSE 8751
ENTRYPOINT ["java","-Xmx200m","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app/gateway.jar"]