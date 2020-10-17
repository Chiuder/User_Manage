package cn.edu.hainu.service.impl;

import java.util.List;
import java.util.Map;

import cn.edu.hainu.dao.UserDao;
import cn.edu.hainu.dao.impl.UserDaoImpl;
import cn.edu.hainu.domain.PageBean;
import cn.edu.hainu.domain.User;
import cn.edu.hainu.service.UserService;

/**
 * @author: 海南大学 王鹏
 * @Email: 1540197193@qq.com
 * @date: 2020/5/30 030
 * @time: 21:12
 * 类说明: 用户业务逻辑的实现类
 */
public class UserServiceImpl implements UserService {
    /**
     调用Dao完成相关功能
     */
    private final UserDao dao = new UserDaoImpl();

    @Override
    //查询所有用户信息
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    //查询登录用户
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    //添加用户
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    //根据ID删除用户
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));
    }

    @Override
    //根据ID查询用户
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    @Override
    //修改用户信息
    public void updateUser(User user) {
        dao.update(user);
    }

    @Override
    //批量删除用户
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            //1.遍历数组
            for (String id : ids) {
                //2.调用dao删除，复用了dao中的delete方法。
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    //在service层的业务逻辑中填充pagebean的参数。并返回PageBean的对象。
    public PageBean<User> findUserByPage(String currentPages, String row) {
        int currentPage = Integer.parseInt(currentPages); // 类型转换
        int rows = Integer.parseInt(row); // 类型转换
        if (currentPage <= 0) {//健壮性检查
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<User> pageBean = new PageBean<User>();

        //2.设置参数
        pageBean.setCurrentPage(currentPage); //添加进pageBean对象中
        pageBean.setRows(rows); //添加进pageBean对象中

        //3.调用dao查询总记录数,实现条件查询
        int totalCount = dao.findTotalCount();
        pageBean.setTotalCount(totalCount); //添加进pageBean对象中

        // 4.调用dao查询List集合
        // 计算开始的记录索引
        int start = (currentPage - 1) * rows;
        //从dao从获取要展示的分页数据
        List<User> list = dao.findByPage(start, rows);
        pageBean.setList(list); //添加进pageBean对象中

        //5.计算总页码，并设置进pageBean对象中
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;
        pageBean.setTotalPage(totalPage); //添加进pageBean对象中
        return pageBean; //返回给web层
    }
}
