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

    // 根据用户名查找登录用户
    public LoginUser getLoginUser(String name) {
        return loginUserDao.getLoginUser(name);
    }

    // 注册登录用户
    public void addLoginUser(LoginUser loginUser) {
        loginUserDao.addLoginUser(loginUser);
    }

    // 修改登录用户
    public void updateLoginUser(LoginUser loginUser) {
        loginUserDao.updataLoginUser(loginUser);
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
