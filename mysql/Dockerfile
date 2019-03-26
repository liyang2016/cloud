FROM mysql:5.7.25

ADD ./initsql /docker-entrypoint-initdb.d

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
ENV LANG=C.UTF-8