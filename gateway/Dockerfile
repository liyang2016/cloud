FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/gateway.jar /app/
COPY ./wait-for-it.sh /usr/local/bin/
COPY ./startup.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/startup.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh
RUN chmod -x /app/*.jar

EXPOSE 8751
#ENTRYPOINT ["java","-Xmx200m","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/app/gateway.jar"]