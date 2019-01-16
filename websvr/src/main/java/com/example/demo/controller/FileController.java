package com.example.demo.controller;

import com.example.demo.domain.FileSaver;
import com.example.demo.service.JsonFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
@RequestMapping(value = "/json")
public class FileController {

    @Autowired
    private JsonFileService jsonFileService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public FileSaver getFileJson(@RequestBody FileSaver fileSaver) {
        System.out.println(fileSaver.getPorvince());

//        return jsonFileService.getData();
        return null;
    }

    // 这个是静态的，而上一行private static JsonFileService jsonFileService； 不能申明静态。就是不能这么用
//    public static void main(String[] args) {
//        System.out.println("######################");

//        这不能new
//        JsonFileService json = new JsonFileService();
//        String json = json.getData();
//        String json = jsonFileService.getData();
//        System.out.println(json);
//    }

    // jsonFileService在static中不可用；
    public FileSaver jsonFile(@RequestBody FileSaver data) {
        String json = jsonFileService.getData();
        System.out.println(json);
        return data;
    }

}
