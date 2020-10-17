package cn.edu.hainu.service;

import java.util.List;
import java.util.Map;

import cn.edu.hainu.domain.PageBean;
import cn.edu.hainu.domain.User;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/5/30 030
 * @time: 21:10
 * 类说明: 用户管理的业务接口
 */
public interface UserService {
    //查询所有用户信息
    public List<User> findAll();

    //登录方法
    User login(User user);

    //添加User
    void addUser(User user);

    //根据ID删除User
    void deleteUser(String id);

    //根据ID查询User
    User findUserById(String id);

    //修改用户信息
    void updateUser(User user);

    //批量删除用户
    void delSelectedUser(String[] ids);

    //分页查询
    PageBean<User> findUserByPage(String currentPage, String rows);
}
