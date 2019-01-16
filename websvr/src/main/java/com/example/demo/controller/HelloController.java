package com.example.demo.controller;

import com.example.demo.domain.Person;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//Spring MVC 属于Spring Boot，Spring Boot是Spring的升级版
@RestController
@RequestMapping(value = "/api")
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello(){
        return "Hello....";
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Person post(@RequestParam(value = "id") String id, @RequestParam(value = "name", required = false) String name){
        Person person = new Person();
        person.setAge(20);
        person.setName("zhang li wei");

        return person;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public List<Person> post2(@RequestBody Person person){
        List<Person> result = new ArrayList<>();
        Person person1 = new Person();
        person1.setAge(20);
        person1.setName("zhang li wei");

        Person person2 = new Person();
        person2.setAge(50);
        person2.setName("fan bo");

        result.add(person1);
        result.add(person2);
        result.add(person);

        return result;
    }

}


