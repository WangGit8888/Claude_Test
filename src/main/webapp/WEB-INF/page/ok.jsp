<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Spring MVC Demo</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-family: "Microsoft YaHei", "PingFang SC", sans-serif;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .card {
            background: #fff;
            border-radius: 16px;
            padding: 48px 64px;
            box-shadow: 0 20px 60px rgba(0, 0, 0, 0.15);
            text-align: center;
            max-width: 480px;
        }
        .card h1 {
            font-size: 28px;
            color: #333;
            margin-bottom: 8px;
        }
        .card .greeting {
            font-size: 20px;
            color: #667eea;
            margin-top: 16px;
        }
        .card .name {
            font-size: 36px;
            font-weight: 700;
            color: #764ba2;
            margin: 12px 0;
        }
        .card .info {
            font-size: 14px;
            color: #999;
            margin-top: 24px;
            border-top: 1px solid #eee;
            padding-top: 16px;
        }
    </style>
</head>
<body>
    <div class="card">
        <h1>🚀 Spring MVC</h1>
        <p class="greeting">你好，欢迎你！</p>
        <p class="name">${name}</p>
        <div class="info">
            <p>这是从 Controller 返回的 ModelAndView 渲染的页面</p>
            <p>View: /WEB-INF/page/ok.jsp</p>
        </div>
    </div>
</body>
</html>
