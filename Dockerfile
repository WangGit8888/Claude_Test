FROM eclipse-temurin:17-jre

# 设置时区
ENV TZ=Asia/Shanghai

# 创建应用目录
WORKDIR /app

# 复制 jar 包
COPY target/*.jar app.jar

# ✅ 新增：复制 JMX Agent JAR 到镜像
#COPY lib/jmx_prometheus_isolator_javaagent-1.6.0.jar /app/jmx-agent.jar

# ✅ 新增：复制 JMX 配置文件
#COPY jmx-config.yaml /app/jmx-config.yaml

# 暴露端口
EXPOSE 8888
EXPOSE 8088

# 启动应用（支持 JAVA_OPTS）
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]