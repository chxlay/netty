FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER Braineex<braineex@gmail.com>

RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime

RUN mkdir -p /braineex-socket

ENV HOME_DIR /braineex-socket

WORKDIR $HOME_DIR

#EXPOSE 10000

ADD ./target/braineex-search.jar ./

CMD sleep 10;java -Djava.security.egd=file:/dev/./urandom -jar braineex-search.jar