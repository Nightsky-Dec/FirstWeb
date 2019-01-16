package com.example.demo.service;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

@Mapper
@Service
public class JsonFileService {

    @Value(value="classpath:json/map/map.json")
    private Resource data;

    public String getData() {
//        data = new ClassPathResource("json/map.json");
        System.out.println("data："+data);
        try {
            File file = data.getFile();
            System.out.println("File："+file);
            long t0 = System.nanoTime();
            String jsonData = this.jsonRead(file);
            long t1 = System.nanoTime();
            long millis = TimeUnit.NANOSECONDS.toMillis(t1-t0);
            System.out.println("Time："+millis + "ms");

            return jsonData;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    /**
     *     读取文件类容为字符串
     * @param file
     * @return
     */
    private String jsonRead(File file){
        Scanner scanner = null;
        StringBuilder buffer = new StringBuilder();
        try {
            scanner = new Scanner(file, "utf-8");
            while (scanner.hasNextLine()) {
                buffer.append(scanner.nextLine());
            }
        } catch (Exception e) {

        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
        return buffer.toString();
    }
}
