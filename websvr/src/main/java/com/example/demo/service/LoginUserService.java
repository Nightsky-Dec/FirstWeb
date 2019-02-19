package com.example.demo.service;


import com.example.demo.dao.LoginUserDao;
import com.example.demo.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginUserService {
    @Autowired
    private LoginUserDao loginUserDao;

    // 查看全部用户
    public List<LoginUser> getUsers() {
        return loginUserDao.getUsers();
    }

    // 根据Uid查找登录用户
    public LoginUser getUserByUid(int uid) {
        return loginUserDao.getUserByUid(uid);
    }

    // 根据用户名查找登录用户
    public LoginUser getUserByName(String name) {
        return loginUserDao.getUserByName(name);
    }

    public LoginUser getUserByToken(String name) {
        return loginUserDao.getUserByToken(name);
    }

    // 添加用户
    public void addLoginUser(LoginUser loginUser) {
        loginUserDao.addUser(loginUser);
    }

    // 修改用户
    public void updateUser(LoginUser loginUser) {
        System.out.println("service:" + loginUser);
        loginUserDao.updataUser(loginUser);
    }

    // 删除登录用户
    public int delLoginUser(Integer id) {
        return loginUserDao.delLoginUser(id);
    }

    //
    public void  updataLoginToken(LoginUser loginUser) {
        loginUserDao.updataLoginToken(loginUser);
    }
}
