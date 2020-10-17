package cn.edu.hainu.web.servlet;

import cn.edu.hainu.domain.User;
import cn.edu.hainu.service.UserService;
import cn.edu.hainu.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/6/13 013
 * @time: 15:53
 * 类说明: 添加用户
 */
@WebServlet(name = "AddUserServlet", urlPatterns = "/AddUserServlet")
public class AddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//设置编码
        //1.获取参数
        Map<String, String[]> map = request.getParameterMap();
        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用UserService中的addUser()方法添加用户
        UserService service = new UserServiceImpl();
        service.addUser(user);
        //4.重定向到UserListServlet
        response.sendRedirect(request.getContextPath() + "/FindUserByPageServlet");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
