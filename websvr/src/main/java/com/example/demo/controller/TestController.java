package com.example.demo.controller;

import com.example.demo.domain.Groups;
import com.example.demo.domain.Person;
import com.example.demo.model.response.Response;
import com.example.demo.service.GroupsService;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private PersonService personService;

    @Autowired
    private GroupsService groupsService;

    // Users
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<Person> getUsers() { return personService.getUsers(); }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Person getUser(@RequestParam Integer id) {
        return personService.getUser(id);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Person post(@RequestBody Person params) {
        System.out.println(params.getName());
        System.out.println(params.getAge());
        personService.addUser(params);
        return params;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Person update(@RequestBody Person params) {
        personService.updataUser(params);
        return params;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public Response delete(@RequestParam Integer id) {
        Response response = new Response();
        personService.delUser(id);
        return response;
    }

    // Groups
    @RequestMapping(value = "/groups", method = RequestMethod.GET)
    public List<Groups> getGroups() { return groupsService.getGroups(); }
}
