package cn.edu.hainu.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import cn.edu.hainu.dao.UserDao;
import cn.edu.hainu.domain.User;
import cn.edu.hainu.util.JDBCUtils;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/5/30 030
 * @time: 21:07
 * 类说明: UserDao的实现类
 */
public class UserDaoImpl implements UserDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    //查询所有用户
    public List<User> findAll() {
        //使用JDBC操作数据库...
        // 1.定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;
    }

    @Override
    //查询登录用户
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            //利用spring框架中的template，实现数据库的用户名和密码的查询，并将结果封装成user对象。
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    //添加用户
    public void add(User user) {
        //1.定义 sql
        String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
        //2.执行 sql
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail());
    }

    @Override
    //修改用户信息
    public void update(User user) {
        //1.定义 sql
        String sql = "update user set name = ?, gender = ?, age = ?, address = ?, qq = ?, email= ? where id = ? ";
        //2.执行 sql
        template.update(sql, user.getName(), user.getGender(), user.getAge(), user.getAddress(), user.getQq(), user.getEmail(), user.getId());
    }

    @Override
    //按ID查找用户
    public User findById(int id) {
        try {
            String sql = "select * from user where id = ? ";
            //利用spring框架中的template，实现数据库用户ID的查询，并将结果封装成user对象。
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    //删除用户
    public void delete(int id) {
        //1.定义 sql
        String sql = "delete from user where id = ? ";
        //2.执行 sql
        template.update(sql, id);
    }

    @Override
    //从数据库获取总的记录数。
    public int findTotalCount() {
        //1.定义模板初始化 sql
        String sql = "select count(*) from user";
        return template.queryForObject(sql, Integer.class);
    }

    @Override
    //从数据库获得在list页面要展示的数据列表对象
    public List<User> findByPage(int start, int rows) {
        String sql = "select * from user limit ?,?";
        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), start, rows);
    }
}
