package com.example.demo;

// 这个本地的操作只能在test里面么？不调用接口不能用main？
//是的，不能掉main
import com.example.demo.domain.FileSaver;
import com.example.demo.domain.TestUser;
import com.example.demo.domain.TestUsers;
import com.example.demo.service.JsonFileService;
import com.example.demo.service.UserService;
import com.example.demo.util.StringUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonTest {

    @Autowired
    private JsonFileService jsonFileService;
    private UserService userService;

    @Test
    public void jsonTest(){

//        User user = loginUserService.getLoginUser("admin");
//        System.out.println(user);

//        fileJson();
//        demo();
//        getJsonObj();
//        testFile();
    }

    public void getJsonObj(){
        String json = jsonFileService.getData();
        // 清除空格
        String reJson = json.replaceAll(" ", "");
        System.out.println("json:" + reJson);
        Map<String, Object> map = StringUtil.StringToJsonMap(reJson);
        System.out.println("map:" + map);
        Object porvince = map.get("porvince");
        System.out.println("porvince:" + porvince);
        Object city = map.get("city");
        System.out.println("city:" + city);
        List<TestUser> aaa = new ArrayList<>();
        aaa.add(new TestUser("小明",15,"北京"));
        map.put("users", aaa);

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File("e:\\new-map.json"),map);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void fileJson() {
        String json = jsonFileService.getData();
        // 清除空格
        String reJson = json.replaceAll(" ", "");
//        System.out.println("json:" + reJson);
        ObjectMapper mapper = new ObjectMapper();
        try {
            FileSaver file = mapper.readValue(reJson,FileSaver.class);
//            FileSaver file = mapper.readValue(new File("e:\\map.json"),FileSaver.class);
            System.out.println("file:" + file);
            ArrayList<Object> porvince = file.getPorvince();
            System.out.println("porvince:" + porvince);
            Object city = file.getCity();
            System.out.println("city:" +city);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public void demo() {
        System.out.println("------------------------------------------------");
        // dome
//        TestUser user = new TestUser("李宁",24,"北京");
//        System.out.println(user);

        ObjectMapper mapper = new ObjectMapper();
        try {
            // 生成Json文件
//            mapper.writeValue(new File("e:\\jackson.json"),user);

            // 转成json字符串
//            String j1 = mapper.writeValueAsString(user);
//            System.out.println("j1:" + j1);
//            TestUser jo = mapper.readValue(j1,TestUser.class);

            // 读取Json文件
            TestUsers jo = mapper.readValue(new File("e:\\jackson.json"), TestUsers.class);
            ArrayList<TestUser> testUsers = jo.getTestUsers();
            System.out.println(testUsers);
            getUser(testUsers);
            TestUser u1 = testUsers.get(1);
            System.out.println("u1:"+u1);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public void getUser(ArrayList<TestUser> testUsers) {
//        for (TestUser u:testUsers) {
//            System.out.println(u);
//            String name = u.getName();
//            Integer age = u.getAge();
//            String location = u.getLocation();
//            System.out.println("姓名：" + name + "；年龄：" + age + "；位置：" + location);
//        }

        for (int i = 0; i< testUsers.size(); i++) {
            TestUser u = testUsers.get(i);
            System.out.println(u);
        }
    }

    public void testFile() {
        File file = new File("e:" + File.separator + "work" + File.separator + "file"); // 路径
        print(file);
    }
    public void print(File file){ // 递归调用
        if(file!=null) { // 判断目录是否为空
            if (file.isDirectory()) { // 判断是否为目录
                File f[] = file.listFiles(); // 列出目录下全部文件
                if (f!=null) { // 判断此目录能否列出
                    for (int i=0;i<f.length;i++) {
                        print(f[i]); // 此路径下可能是目录
                    }
                }
            } else {
                System.out.println(file); // 不是文件夹则是文件，输出文件路径
            }
        }
    }
}
