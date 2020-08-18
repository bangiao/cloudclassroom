#!/bin/bash
echo "判断docker环境，如果存在相同容器和镜像则删除****"
PROJECT="classroom"
if docker ps -a | grep -i $PROJECT ; then

	docker rm -f $PROJECT
	docker rmi -f $PROJECT

	echo "==>>删除已存在的容器和镜像"

	else

	echo "==>>不存在工程容器"
fi
echo "build docker镜像开始..."
docker build -t classroom .
docker run -p 8089:8089 -it --name classroom -d classroom
echo "****容器启动成功：云课堂：8089"