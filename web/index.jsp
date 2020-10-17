<%--
  Created by IntelliJ IDEA.
  User: 海南大学 王鹏 1540197193@qq.com
  Date: 2020/5/30 030
  Time: 20:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>首页</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
    </script>
</head>
<body>
<h1>&nbsp;${user.name},欢迎您！</h1>
<div align="center">
    <a href="${pageContext.request.contextPath}/FindUserByPageServlet"
       style="text-decoration:none;font-size:33px">查询所有用户信息</a>
</div>
</body>
</html>
