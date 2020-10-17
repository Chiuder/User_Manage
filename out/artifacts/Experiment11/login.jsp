<%--
Created by IntelliJ IDEA.
User: 海南大学 王鹏 1540197193@qq.com
Date: 2020/5/19 019
Time: 14:09
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员登录</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript">
        /*
          分析：点击图片，需要换一张
          1.给图片绑定单击事件
          2.重新设置图片的src属性值
          3.为了防止使用缓存中的图片，加时间戳参数，让每次请求的路径不一样，这样就会欺骗服务器，每次都会去执行新的请求。
          time="+ new Date().getTime()传递的参数没有实际意义，就是为了使得每次请求不一样，欺骗服务器。
          加时间戳参数的原因：让每次请求的路径永不重复。
        */
        function refreshCode() {
            //1.获取验证码图片对象
            var vcode = document.getElementById("vcode");
            //2.设置其src属性，加时间戳
            vcode.src = "CheckCodeServlet?time=" + new Date().getTime();
        }
    </script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员登录</h3>
    <form action="LoginServlet" method="post">
        <div class="form-group">
            <label for="username">用户名：</label>
            <input type="text" name="username" class="form-control" id="username" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="verifyCode" class="form-control" id="verifyCode" placeholder="请输入验证码"
                   style="width: 120px;"/>
            <a href="javascript:refreshCode();">
                <img src="CheckCodeServlet" title="看不清点击刷新" id="vcode" alt="">
            </a>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录">
        </div>
    </form>
    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
        <strong>${login_msg}</strong>
    </div>
</div>
</body>
</html>