package cn.edu.hainu.web.servlet;

import cn.edu.hainu.service.UserService;
import cn.edu.hainu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/6/24 024
 * @time: 15:32
 * 类说明: 删除选定的用户
 */
@WebServlet(name = "DelSelectedServlet", urlPatterns = "/DelSelectedServlet")
public class DelSelectedServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//设置编码
        //1.获取所有选中的id
        String[] ids = request.getParameterValues("uid");

        //2.调用UserService中的delSelectedUser()方法批量删除用户
        UserService service = new UserServiceImpl();
        service.delSelectedUser(ids);
        //3.重定向到查询所有用户Servlet
        response.sendRedirect(request.getContextPath() + "/FindUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
