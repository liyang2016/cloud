FROM java:8-jre
MAINTAINER Liyang <cp3leon@gmail.com>

ADD ./target/account-service.jar /app/
COPY ../wait-for-it.sh /app/
COPY ../startup.sh /startup.sh

RUN chmod +x /app/wait-for-it.sh
RUN chmod +x /startup.sh

EXPOSE 6000

ENTRYPOINT ["/startup.sh","docker","account-service"]