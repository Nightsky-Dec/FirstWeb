package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.model.response.Response;
import com.example.demo.service.UserService;
import com.example.demo.service.RedisService;
import com.example.demo.util.RedisUtils;
import com.example.demo.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisUtils redisUtils;

    // 获取登录用户
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> post(@RequestBody User params, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Map<String, Object> result = new HashMap<>();
        System.out.println("params:" + params);
        String name = params.getName();
        String pass = params.getPass();

        // 数据库中存储的用户
        User user = userService.getUserByName(name);
        if (user != null) {
            System.out.println("user:" + user);
            String userName = user.getName();
            String userPass = user.getPass();
            int uid = user.getUid();
            if (userName.equals(name) && userPass.equals(pass)) {
                // 生成token传给前端存储做登录状态-暂存入数据库
                String token = WebUtils.makeId();
                try {
                    // redis存储token，注意redis的写权限
                    // key: token; value: uid
                    redisUtils.set(token,uid);
                } catch (Exception e) {
                    System.out.println(e);
                    // token存入mySql
//                    user.setToken(token);
//                    loginUserService.updataLoginToken(user);
                }

                result.put("token", token);
                result.put("status", true);
                result.put("userName", userName);
                result.put("uid", uid);
                return result;
            } else {
                result.put("status", false);
                result.put("message", "密码错误！！");
                return result;
            }
        } else {
            result.put("message","用户不存在！！");
            result.put("status", false);
            return result;
        }
    }

    // 用户登出
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Response LogoutUser(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Response response = new Response();

        String token = req.getHeader("x-auth-token");
        try {
            redisUtils.del(token);
            return response;
        } catch (Exception e) {
            System.out.println(e);
            response.setStatus(0);
            response.setMassage("error:" + e);
            return response;
        }
    }
}
