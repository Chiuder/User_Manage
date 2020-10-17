<%--
  Created by IntelliJ IDEA.
  User: 海南大学 王鹏 1540197193@qq.com
  Date: 2020/5/30 030
  Time: 21:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="zh-CN">      <!-- 网页使用的语言 -->
<head>
    <meta charset="utf-8">    <!-- 指定字符集 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">   <!-- 使用Edge最新的浏览器的渲染方式 -->
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <link href="css/bootstrap.min.css" rel="stylesheet">     <!-- 1. 导入CSS的全局样式 -->
    <script src="js/jquery-2.1.0.min.js"></script>     <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/bootstrap.min.js"></script>    <!-- 3. 导入bootstrap的js文件 -->
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        function deleteUser(id, name) {
            //用户安全提示
            if (confirm("您确定要删除" + name + "吗？")) {
                //访问路径
                location.href = "${pageContext.request.contextPath}/DelSelectedServlet?id=" + id;
            }
        }

        window.onload = function () {//window.onload的功能是让页面加载完后再获取按钮id。
            // 给删除选中按钮添加单击事件
            document.getElementById("delSelected").onclick = function () {
                var flag = false;
                //判断是否有选中条目
                var cbs = document.getElementsByName("uid");
                for (var i = 0; i < cbs.length; i++) {
                    if (cbs[i].checked) {
                        //有一个条目选中了
                        flag = true;
                        break;
                    }
                }

                if (!flag)//说明没有条目被选中，则不执行任何操作，直接返回。
                    return;
                if (confirm("您确定要删除选中条目吗？")) {
                    //表单提交
                    document.getElementById("form").submit();
                }
            }

            //1.获取第一个复选框cb
            document.getElementById("firstCb").onclick = function () {
                //2.获取下边列表中所有的cb
                var cbs = document.getElementsByName("uid");
                //3.遍历
                for (var i = 0; i < cbs.length; i++) {
                    //4.设置这些cbs[i]的checked状态 = firstCb.checked
                    cbs[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>
    <div style="float: left;">
        <form class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" id="exampleInputName2">
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" class="form-control" id="exampleInputName3">
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" class="form-control" id="exampleInputEmail2">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>
    <div style="float: right;margin: 5px;">
        <a class="btn btn-primary" href="add.jsp">添加用户</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>
    </div>

    <form id="form" action="DelSelectedServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${pageBean.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                    <td>${s.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td>
                        <a class="btn btn-default btn-sm"
                           href="update.html">修改 </a>&nbsp;
                        <a class="btn btn-default btn-sm"
                           href="javascript:deleteUser(${user.id}, '${user.name}');">删除</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <c:if test="${pageBean.currentPage == 1}">
            <li class="hide"></c:if>
                <c:if test="${pageBean.currentPage != 1}">
            <li></c:if>
                <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pageBean.currentPage - 1}"
                   aria-label="Previous">  <!--上一页，修改href--> <span aria-hidden="true">&laquo;</span> </a>
            </li>

            <c:forEach begin="1" end="${pageBean.totalPage}" var="i">
                <c:if test="${pageBean.currentPage == i}"> <!--选中的页码显示样式变为激活状态-->
                    <li class="active"><a
                            href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}">${i}</a>
                    </li>
                </c:if>
                <c:if test="${pageBean.currentPage != i}"> <!--未选中的页码-->
                    <li>
                        <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}">${i}</a>
                    </li>
                </c:if>
            </c:forEach>

            <c:if test="${pageBean.currentPage==pageBean.totalPage}">
            <li class="hide"></c:if>
            <c:if test="${pageBean.currentPage!=pageBean.totalPage}">
            <li></c:if>
                <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pageBean.currentPage + 1}"
                   aria-label="Next"> <!--下一页，修改href-->
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <span style="font-size: 20px;margin-left: 10px;">共${pageBean.totalCount}条记录，共${pageBean.totalPage}页 </span>
        </ul>
    </nav>
</div>
</body>
</html>
