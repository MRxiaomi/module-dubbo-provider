# 版本信息
FROM tomcat:8-jdk11-openjdk-slim
MAINTAINER liuyumeng <liuyumeng@tinman.cn>
# 工作目录
#WORKDIR /var/

# 添加解压jdk
#ADD jdk-8u191-linux-x64.tar.gz .
# 添加解压tomcat
#ADD apache-tomcat-8.5.42.tar.gz .
COPY target/dubbo-provider-*-SNAPSHOT.war /usr/local/tomcat/webapps/demo.war

# 设置环境变量
#ENV JAVA_HOME /var/jdk1.8.0_191
#ENV PATH $PATH:$JAVA_HOME/bin:$CATALINA_HOME/bin
#ENV TIME_ZONE Asia/Shanghai

# 更改时区
#RUN set -x \
#&& echo "${TIME_ZONE}" > /etc/timezone \
#&& ln -sf /usr/share/zoneinfo/${TIME_ZONE} /etc/localtime

# 开启内部服务端口
EXPOSE 20880

# 启动tomcat服务器 并输出日志
CMD ["catalina.sh", "run"]