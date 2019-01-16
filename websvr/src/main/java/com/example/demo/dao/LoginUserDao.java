package com.example.demo.dao;

import com.example.demo.domain.LoginUser;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface LoginUserDao {
    // 查看全部用户
    @Select("select * from user_info")
    public List<LoginUser> getUsers();

    // 根据用户名查找用户
    @Select("select * from user_info where name=#{name}")
    public LoginUser getLoginUser(String name);

    // 增添用户
    @Insert("insert into user_info(name,pass,email,age) value(#{name},#{pass},#{email},#{age})")
    public void addLoginUser(LoginUser loginUser);

    // 修改用户
    @Update("update user_info set name=#{name},pass=#{pass},email=#{email},age=#{age} where uid=#{uid}")
    public void updataLoginUser(LoginUser loginUser);

    // 删除用户
    @Delete("delete from user_info where uid=#{uid}")
    public int delLoginUser(Integer id);

    // 修改用户Token
    @Update("update user_info set token=#{token} where uid=#{uid}")
    public void updataLoginToken(LoginUser loginUser);
}
