package com.example.demo.dao;

import com.example.demo.domain.Person;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PersonDao {

    @Select("select * from tab_users")
    public List<Person> getUsers();

    @Select("select * from tab_users where id = #{id}")
    public Person getUser(Integer id);

//    @Insert("insert **** into tab_users")
//    public int addPerson();
    @Insert("INSERT INTO tab_users(name,age) VALUES(#{name}, #{age})")
    public void addUser(Person person);
//    public Person addUser(String name, Integer age);

    @Update("UPDATE tab_users SET name=#{name},age=#{age} WHERE id=#{id}")
    public void updateUser(Person person);

    @Delete("delete from tab_users where id = #{id}")
    public int delUser(Integer id);
}
