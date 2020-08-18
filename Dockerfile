FROM java:8-alpine
MAINTAINER jianghuaidi
MAINTAINER 601679080@qq.com
COPY classroom-web/target/classroom-web-1.0.0-SNAPSHOT.jarr /home/dockerimages/classroom.jar
EXPOSE 8089
CMD ["java","-jar","/home/dockerimages/classroom.jar","--spring.profiles.active=dev"]