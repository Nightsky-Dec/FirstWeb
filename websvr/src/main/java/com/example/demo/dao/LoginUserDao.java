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
    @Select("select * from user_info where uid=#{uid}")
    public LoginUser getUserByUid(int uid);

    // 根据用户名查找用户
    @Select("select * from user_info where name=#{name}")
    public LoginUser getUserByName(String name);

    // 根据token查找用户，确定用户是否在线
    @Select("select * from user_info where token=#{token}")
    public LoginUser getUserByToken(String token);

    // 增添用户
    @Insert("inster into user_info(name,pass.email,age,phone,avator,remakes) value(#{name},#{pass},#{email},#{age},#{phone},#{avator},#{remakes})")
    public void addUser(LoginUser loginUser);

    // 修改用户
    @Update("update user_info set name=#{name},pass=#{pass},email=#{email},age=#{age},phone=#{phone},avator=#{avator},remakes=#{remakes} where uid=#{uid}")
    public void updataUser(LoginUser loginUser);

    // 删除用户
    @Delete("delete from user_info where uid=#{uid}")
    public int delLoginUser(Integer id);

    // 修改用户Token
    @Update("update user_info set token=#{token} where uid=#{uid}")
    public void updataLoginToken(LoginUser loginUser);
}
