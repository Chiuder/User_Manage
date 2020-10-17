package cn.edu.hainu.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/6/17 017
 * @time: 16:39
 * 类说明: 登录验证判断，使用户未经登录不能直接访问资源
 */
@WebFilter(filterName = "VerifyLoginFilter", urlPatterns = "*.jsp")
public class VerifyLoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException { }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //1.转换req和resp
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        //2.提取到网页的url地址
        String url = String.valueOf(request.getRequestURL());
        //3.登录和注册不用过滤，排除图片，验证码等资源，直接放行
        if(url.contains("/login.jsp") || url.contains("/LoginServlet")|| url.contains("/css/") || url.contains("/js/") || url.contains("/CheckCodeServlet")) {
            chain.doFilter(req, resp);
            return;
        }

        //4.获取session
        HttpSession session = request.getSession();
        //5.提取session中的username,如果有说明已经登录了
        if(session.getAttribute("user") == null){
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8"); // 转码
            //session中没有username，说明没有登录，跳转登录验证
            PrintWriter out = response.getWriter();
            out.print("<script language='javascript'>alert('您尚未登录，请先登录！');window.location.href='login.jsp';</script>");
        }else {
            //6.session中有了username，说明已经登录，直接放行
            chain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() { }
}
