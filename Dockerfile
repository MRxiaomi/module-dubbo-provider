# 版本信息
FROM tomcat:8.5-jdk8-openjdk
MAINTAINER liuyumeng <liuyumeng@tinman.cn>
# 工作目录
#WORKDIR /var/

# 添加解压jdk
#ADD jdk-8u191-linux-x64.tar.gz .
# 添加解压tomcat
#ADD apache-tomcat-8.5.42.tar.gz .
COPY target/dubbo-provider-*.war /usr/local/tomcat/webapps/demo1.war


RUN apk add --update tzdata
ENV TZ=Asia/Shanghai

# 开启内部服务端口
EXPOSE 8080

# 启动tomcat服务器 并输出日志
CMD ["catalina.sh", "zrun"]