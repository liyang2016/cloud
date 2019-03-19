FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/account-service.jar /app/

EXPOSE 6000
ENTRYPOINT ["java","-Xmx200m","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app/account-service.jar"]