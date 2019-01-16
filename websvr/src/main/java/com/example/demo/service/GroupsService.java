package com.example.demo.service;

import com.example.demo.dao.GroupsDao;
import com.example.demo.domain.Groups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupsService {

    @Autowired
    private GroupsDao groupsDao;

    public List<Groups> getGroups(){
        return groupsDao.getGroups();
    }
}
