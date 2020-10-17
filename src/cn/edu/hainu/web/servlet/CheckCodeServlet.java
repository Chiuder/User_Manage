package cn.edu.hainu.web.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/5/19 019
 * @time: 14:16
 * 类说明: 生成验证码图片
 */
@WebServlet(name = "CheckCodeServlet", urlPatterns = "/CheckCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //服务器通知浏览器不要缓存
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setHeader("expires", "0");

        int width = 85;  //验证码图片宽
        int height = 30;  //验证码图片高

        //1.创建一对象，在内存中代表一张图片(验证码图片对象)，图片格式使用RGB
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        //2.美化图片
        //2.1 填充背景色
        Graphics g = image.getGraphics();//画笔对象
        g.setColor(Color.LIGHT_GRAY);//设置画笔颜色
        g.fillRect(0, 0, width, height); //颜色填充
        //2.2产生4个随机验证码，12Ey
        String checkCode = getCheckCode();
        //2.3 将验证码存入 session
        request.getSession().setAttribute("checkCode_session", checkCode);
        //2.4 画边框
        g.setColor(Color.BLUE);
        g.setFont(new Font("微软雅黑", Font.BOLD, 24));  //设置字体和大小
        g.drawString(checkCode, 10, 25);
        //2.5 画干扰线
        g.setColor(Color.GRAY);
        //随机生成坐标点
        Random ran = new Random();  // 创建一个随机对象
        for (int i = 0; i < 10; i++) {
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(height);
            int y2 = ran.nextInt(height);
            g.drawLine(x1, y1, x2, y2);  //用画笔画干扰线
        }
        //3.将图片输出到页面展示
        ImageIO.write(image, "jpg", response.getOutputStream());
    }

    //生成验证码
    private String getCheckCode() {
        // str：用来随机产生字母和数字用的。
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghigklmnopqrstuvwxyz0123456789";
        Random ran = new Random();  // 创建一个随机对象
        StringBuilder sb = new StringBuilder();  // StringBuilder：可追加字符的字符串变量，最初始是空的
        // 用 for 循环得到四个随机字符
        for (int i = 1; i <= 4; i++) {
            int index = ran.nextInt(str.length());//生成随机下标 index（0~str.length）
            char ch = str.charAt(index);  //获取一个随机字符
            sb.append(ch);  //追加该验证码字符到 sb 变量中。
        }
        return sb.toString();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
