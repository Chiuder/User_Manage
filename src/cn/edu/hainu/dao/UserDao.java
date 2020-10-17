package cn.edu.hainu.dao;

import java.util.List;

import cn.edu.hainu.domain.User;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/5/30 030
 * @time: 21:05
 * 接口说明: 用户操作的 DAO
 */
public interface UserDao {
    //查询所有用户
    public List<User> findAll();

    //查询登录用户
    User findUserByUsernameAndPassword(String username, String password);

    //添加用户
    void add(User user);

    //修改用户信息
    void update(User user);

    //删除用户
    void delete(int id);

    //按ID查找用户
    User findById(int id);

    //查询总记录数
    int findTotalCount();

    //分页查询每页记录
    List<User> findByPage(int start, int rows);
}
