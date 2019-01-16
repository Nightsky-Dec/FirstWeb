package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.domain.Person;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonDao personDao;

    public List<Person> getUsers(){
        return personDao.getUsers();
    }

    public Person getUser(Integer id) {
        return personDao.getUser(id);
    }

//    public Person addUser(String name, Integer age) {
//        System.out.println("sever:" + name);
//        System.out.println("sever:" + age);
//        return personDao.addUser(name, age);
//    }
    public void addUser(Person person) {
        personDao.addUser(person);
    }

    public void updataUser(Person person) {
        personDao.updateUser(person);
    }

    public int delUser(Integer id) {
        return personDao.delUser(id);
    }
}
