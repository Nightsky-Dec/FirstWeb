package com.example.demo.controller;

import com.example.demo.domain.LoginUser;
import com.example.demo.model.response.ObjectResponse;
import com.example.demo.model.response.Response;
import com.example.demo.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private LoginUserService loginUserService;

    // 查看用户
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ObjectResponse getUser(@RequestParam String name) {
        ObjectResponse response = new ObjectResponse();
        LoginUser user = loginUserService.getUserByName(name);
        response.setData(user);
        return response;
    }

    // 查看全部用户
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Map<String, Object> getUsers() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<LoginUser> users = loginUserService.getUsers();
            result.put("list", users);
            result.put("status", 1);
        } catch (Exception e) {
            result.put("status", 0);
            result.put("message", "error: " + e);
        }
        return result;
    }

    // 注册用户
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> addLoginUser(@RequestBody LoginUser params, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        Map<String, Object> result = new HashMap<>();

        // 校验数据库中是否已存在同名用户
        LoginUser hasName = loginUserService.getUserByName(params.getName());
        if (hasName != null) {
            result.put("message", "该用户名已注册！！");
            result.put("status", false);
            return result;
        }

        // 校验用户注册填写的表单数据
        if (params.validate() == false) { // 如果校验失败，返回错误信息
            Map<String, Object> error = params.getErrors();
            return error;
        }

        try {
            // 调用接口时间
//            SimpleDateFormat addTime = new SimpleDateFormat("yyyy-MM-dd");
            loginUserService.addLoginUser(params);
            result.put("message", "注册成功！！");
            result.put("status", true);
            return result;
        } catch (Exception e) {
            e.printStackTrace(); // 后台记录异常
            result.put("message", "error:" + e);
            result.put("status", false);
            return result;
        }
    }

    // 修改用户
    @RequestMapping(value = "/updata", method = RequestMethod.POST)
    public Response updataLoginUser(@RequestBody LoginUser loginUser, HttpServletRequest req) {
        Response response = new Response();

        int uid = loginUser.getUid();
        LoginUser user = loginUserService.getUserByUid(uid);
        /**
         * 用户存在则继续操作
         * 客户端数据中，存在的项替换数据库中的数据，否则沿用数据库中的数据
         * */
        if (user.getName() != null) {
            if (loginUser.getName() != null) {
                user.setName(loginUser.getName());
            }
            if (loginUser.getPass() != null) {
                user.setPass(loginUser.getPass());
            }
            if (loginUser.getEmail() != null) {
                user.setEmail(loginUser.getEmail());
            }
            if (loginUser.getAge() !=null) {
                user.setAge(loginUser.getAge());
            }
            if (loginUser.getPhone() != null) {
                user.setPhone(loginUser.getPhone());
            }
            if (loginUser.getAvator() != null) {
                user.setAvator(loginUser.getAvator());
            }
            if (loginUser.getRemakes() != null) {
                user.setRemakes(loginUser.getRemakes());
            }
        } else {
            response.setStatus(0);
            response.setMassage("修改失败，用户不存在！");
            return response;
        }

        try {
            loginUserService.updateUser(user);
            return response;
        } catch (Exception e) {
            response.setStatus(0);
            response.setMassage("error:" + e);
            return response;
        }
    }

    // 删除用户
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public LoginUser delLoginUser(@RequestParam Integer id) {
        loginUserService.delLoginUser(id);
        return null;
    }
}
