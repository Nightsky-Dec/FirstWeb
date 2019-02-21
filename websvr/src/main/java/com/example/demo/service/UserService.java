package com.example.demo.service;


import com.example.demo.dao.UserDao;
import com.example.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    // 查看全部用户
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    // 根据Uid查找登录用户
    public User getUserByUid(int uid) {
        return userDao.getUserByUid(uid);
    }

    // 根据用户名查找登录用户
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    public User getUserByToken(String name) {
        return userDao.getUserByToken(name);
    }

    // 添加用户
    public void addLoginUser(User user) {
        userDao.addUser(user);
    }

    // 修改用户
    public void updateUser(User user) {
        System.out.println("service:" + user);
        userDao.updataUser(user);
    }

    // 删除登录用户
    public int delLoginUser(Integer id) {
        return userDao.delLoginUser(id);
    }

    //
    public void  updataLoginToken(User user) {
        userDao.updataLoginToken(user);
    }
}
