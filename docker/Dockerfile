FROM java:8-jre
MAINTAINER gengmoran

ADD right-hand.jar /app/
RUN ["cp", "/usr/share/zoneinfo/Asia/Shanghai","/etc/localtime"]
CMD ["java", "-Duser.timezone=GMT+8","-Xmx200m", "-jar", "/app/right-hand.jar"]

EXPOSE 8080