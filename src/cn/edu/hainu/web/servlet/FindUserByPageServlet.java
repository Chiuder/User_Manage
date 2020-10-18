package cn.edu.hainu.web.servlet;

import cn.edu.hainu.domain.PageBean;
import cn.edu.hainu.domain.User;
import cn.edu.hainu.service.UserService;
import cn.edu.hainu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/7/1 001
 * @time: 16:15
 * 类说明: 分页查询
 */
@WebServlet(name = "FindUserByPageServlet", urlPatterns = "/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");//设置编码
        //1.获取参数
        String currentPage = request.getParameter("currentPage");//当前页码
        String rows = request.getParameter("rows");//每页显示条数

        //如果当前页面为空或null时，将当前页面设定为1
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }

        //如果页面显示的行数为空或null时，将行数设定为6
        if (rows == null || "".equals(rows)) {
            rows = "6";
        }

        //2.调用service查询，通过 调用service层的方法获得PageBean对象。该对象在jsp中要使用
        UserService service = new UserServiceImpl();
        //指定泛型T的类型为User。
        PageBean<User> pageBean = service.findUserByPage(currentPage, rows);
        System.out.println(pageBean);

        //3.将PageBean作为共享数据存入request作用域中
        request.setAttribute("pageBean", pageBean);

        //4.请求转发到list.jsp
        request.getRequestDispatcher("/list.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
