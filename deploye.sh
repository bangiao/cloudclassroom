#!/bin/bash


pids=`ps -ef|grep classroom|grep -v grep|awk '{printf $2}'`
echo $pids
#|sudo xargs kill -9
for i in $pids
do
        sudo kill -9  $i
done
ps -ef|grep classroom-web-1.0.0-SNAPSHOT.jar | grep -v grep

DATE=`date +%Y-%m-%d-%H-%M` #获取当前系统时间

if [ -f /home/ykt/app/log.log ];then
  sudo mv /home/ykt/app/log.log /home/ykt/app/bk_logs/${DATE}_log.log
fi

nohup java -jar -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5009 /var/lib/jenkins/workspace/云课堂/classroom-web/target/classroom-web-1.0.0-SNAPSHOT.jar --spring.profiles.active=dev > /home/ykt/app/log.log 2>&1 &
