FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/order-service.jar /app/
COPY ./wait-for-it.sh /usr/local/bin/
COPY ./startup.sh /usr/local/bin/

RUN chmod +x /usr/local/bin/wait-for-it.sh
RUN chmod +x /usr/local/bin/startup.sh

EXPOSE 6005