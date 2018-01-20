FROM alpine:3.7
RUN apk --update add openjdk8-jre
ADD target/supplyservice.jar /opt/service/supplyservice.jar
EXPOSE 8081

CMD java -jar /opt/service/supplyservice.jar
