FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/registry.jar /app/

ADD ./wait-for-it.sh /usr/local/bin/
ADD ./startup.sh /usr/local/bin/
RUN chmod +x /usr/local/bin/startup.sh
RUN chmod +x /usr/local/bin/wait-for-it.sh
RUN chmod -x /app/*.jar

EXPOSE 8761

#ENTRYPOINT ["startup.sh","docker","registry"]
