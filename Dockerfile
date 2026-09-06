FROM eclipse-temurin:17-jre

# 设置时区
ENV TZ=Asia/Shanghai

# 创建应用目录
WORKDIR /app

# 复制 jar 包
COPY target/*.jar app.jar

# 暴露端口（根据你的 Spring Boot 配置调整）
EXPOSE 8888

# 启动应用
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar /app/app.jar"]