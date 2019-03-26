FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/zipkin.jar /app/
COPY ./wait-for-it.sh /usr/local/bin/
COPY ./startup.sh /usr/local/bin/

RUN chmod +x /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh

EXPOSE 8761

#ENTRYPOINT ["java","-Xmx200m","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app/zipkin.jar"]