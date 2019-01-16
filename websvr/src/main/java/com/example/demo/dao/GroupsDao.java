package com.example.demo.dao;

import com.example.demo.domain.Groups;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GroupsDao {

    @Select("select * from users_groups")
    public List<Groups> getGroups();
}
