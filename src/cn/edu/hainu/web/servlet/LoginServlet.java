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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/5/19 019
 * @time: 15:00
 * 类说明: 获取用户输入的用户名、密码、验证码，并进行判断
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//设置编码
        //1.获取用户填写的验证码
        String verifyCode = request.getParameter("verifyCode"); //获取用户输入的验证码
        //2.先从 session 获取生成的验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        //删除 session 中存储的验证码，确保验证码是一次性的。
        session.removeAttribute("checkCode_session");
        //3.先判断验证码是否正确
        if (checkCode_session != null && checkCode_session.equalsIgnoreCase(verifyCode)) { //忽略大小写比较
            //验证码校验正确则继续判断用户名与密码
            Map<String, String[]> map = request.getParameterMap();
            //4.封装User对象
            User user = new User();
            try {
                BeanUtils.populate(user, map);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            //5.调用Service查询
            UserService service = new UserServiceImpl();
            User loginUser = service.login(user);
            //6.判断是否登录成功
            if (loginUser != null) {
                //登录成功，将用户存入session
                session.setAttribute("user", loginUser);
                //跳转页面
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            } else {
                //登录失败，提示信息
                request.setAttribute("login_msg", "用户名或密码错误！");
                //跳转登录页面
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        } else {
            //验证码不一致
            //存储提示信息到 request 域
            request.setAttribute("login_msg", "验证码错误！");
            //4.转发到登录页面，注意转发不需要写虚拟目录
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
