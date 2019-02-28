package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/download")
public class DownloadController {

    @Value("${upload.file.path}")
    private String file_path; // 文件上传的根目录

    private final ResourceLoader resourceLoader;

    @Autowired
    public DownloadController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    /**
     * 图片下载
     *
     * */
    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public ResponseEntity<?> download(@PathVariable String filename){
        try {
            String path = Paths.get(file_path, filename).toString();
            Resource resource = resourceLoader.getResource("file:" + path);
            return ResponseEntity.ok(resource);
        } catch (Exception e) {
            throw e;
        }
    }
}
