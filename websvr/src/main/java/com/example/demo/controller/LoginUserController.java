package com.example.demo.controller;

import com.example.demo.bean.RegisterFormBean;
import com.example.demo.domain.LoginUser;
import com.example.demo.service.LoginUserService;
import com.example.demo.service.RedisService;
import com.example.demo.util.RedisUtils;
import com.example.demo.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class LoginUserController {
    /**
     *
     */
    @Autowired
    private LoginUserService loginUserService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisUtils redisUtils;

    // 获取登录用户
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, Object> post(@RequestBody LoginUser params, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        System.out.println("params:" + params);
        String name = params.getName();
        String pass = params.getPass();

        // 数据库中存储的用户
        Map<String, Object> result = new HashMap<>();
        LoginUser user = loginUserService.getLoginUser(name);
        if (user != null) {
            System.out.println("user:" + user);
            String userName = user.getName();
            String userPass = user.getPass();
            int uid = user.getUid();
            if (userName.equals(name) && userPass.equals(pass)) {
                // 生成token传给前端存储做登录状态-暂存入数据库
//                String token = WebUtils.makeId();
//                user.setToken(token);
//                loginUserService.updataLoginToken(user);
                String token = "testdddd";
                /**
                 * redis存储token，注意redis的写权限
                 * */
                redisUtils.set(token,token);
//                Object redisTK = redisService.get(token);
//                System.out.println("redisTK:" + redisTK);

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

        /**
         * 获取客户端信息
         * */
//        showHttpServletInfo(req, res);
//        showHttpServletHeader(req, res);
//        showHttpServletParam(req, res);
    }

    // 用户登出
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public Map<String, Object> LogoutUser(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
//        showHttpServletHeader(req, res);
        String token = req.getHeader("x-auth-token");
        System.out.println("token: " + token);

        Object tk = redisUtils.get(token);
        System.out.println("tk: " + tk);

        Map<String, Object> result = new HashMap<>();
        // 查找数据库中对应的用户
//        LoginUser user = loginUserService.getUserByToken(token);

        if (tk != null) {
            try {
//                user.setToken(null);
//                loginUserService.updataLoginToken(user);
                redisUtils.del(token);

                result.put("status", true);
                result.put("message", "注销成功！！");
                return result;
            } catch (Exception e) {
                result.put("status", false);
                result.put("message", "注销失败！！");
                return result;
            }
        } else {
            result.put("status", false);
            result.put("message", "注销失败！！");
            return result;
        }
    }

    // 注册登录用户
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map<String, Object> addLoginUser(@RequestBody LoginUser params, HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        // 将客户端提交的数据封装到RegisterFormBean对象中
        System.out.println(params);
        Map<String, Object> result = new HashMap<>();

//        RegisterFormBean formbean = WebUtils.request2Bean(req, RegisterFormBean.class);
        // 校验数据库中是否已存在同名用户
        LoginUser hasName = loginUserService.getLoginUser(params.getName());
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
            result.put("message", "注册失败！！");
            result.put("status", false);
            return result;
        }
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
            result.put("message", e);
        }
        return result;
    }

    // 修改登录用户
    @RequestMapping(value = "/updata", method = RequestMethod.POST)
    public LoginUser updataLoginUser(@RequestBody LoginUser loginUser) {
        loginUserService.updateLoginUser(loginUser);
        return loginUser;
    }

    // 删除登录用户
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public LoginUser delLoginUser(@RequestParam Integer id) {
        loginUserService.delLoginUser(id);
        return null;
    }

    public void showHttpServletInfo(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        /**
         * 获得客户机请求信息
         * */
        String requestUrl = req.getRequestURL().toString();// 得到请求的URL地址
        String requestUri = req.getRequestURI();// 得到请求的资源
        System.out.println("请求的URL地址:" + requestUrl);
        System.out.println("请求的资源:" + requestUri);
        res.setCharacterEncoding("UTF-8"); // 设置将字符以"UTF-8"编码输出到客户端浏览器
        // 通过设置响应头控制浏览器以UTF-8的编码显示数据，如果不加这句话，那么浏览器显示的将是乱码
        res.setHeader("content-type", "text/html;charset=UTF-8");
        /**
         * PrintWriter
         * 这个方法的作用的话，感觉可以用在比如在提交form表单使用action的方式提交，
         * 因为你使用action方式通过按钮submit提交表单的话是没有返回值的（当然你可以通过引入js来实现）。
         * */
//        PrintWriter out = res.getWriter();
//        out.write("获取到的客户机信息如下：");
//        out.write("<hr/>");
//        out.write("请求的URL地址："+requestUrl);
//        out.write("<hr/>");
//        out.write("请求的资源："+requestUri);
        // flush()表示强制将缓冲区中的数据发送出去,不必等到缓冲区满
//        out.flush();
//        out.close();
    }
    public void showHttpServletHeader(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
            /**
             * 获取客户端请求头信息
             * */
        res.setCharacterEncoding("UTF-8");// 设置将字符以"UTF-8"编码输出到客户端浏览器
        // 通过设置响应头控制浏览器以UTF-8的编码显示数据
        res.setHeader("content-type", "text/html;charset=UTF-8");
        Enumeration<String> reqHeadInfos = req.getHeaderNames(); // 获取所有的请求头
//        PrintWriter out = res.getWriter();
//        out.write("获取到的客户端所有的请求头信息如下：");
//        out.write("<hr/>");
        while (reqHeadInfos.hasMoreElements()) {
            String headName = (String) reqHeadInfos.nextElement();
            String headValue = req.getHeader(headName); // 根据请求头的名字获取对应的请求头的值
            System.out.println(headName + ": " + headValue);
//            out.write(headName + ": " + headValue);
//            out.write("<br/>");
        }
//        out.write("<br/>");
//        out.write("获取到的客户端Accept-Encoding请求头的值：");
//        out.write("<hr/>");
        String value = req.getHeader("Accept-Encoding");//获取Accept-Encoding请求头对应的值
        System.out.println(value);
//        out.write(value);

        Enumeration<String> e = req.getHeaders("Accept-Encoding");
        while (e.hasMoreElements()) {
            String string = (String) e.nextElement();
            System.out.println(string);
        }
//        out.flush();
//        out.close();
    }

    public void showHttpServletParam(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException{
        /**
         * 获得客户机请求参数(客户端提交的数据)
         * */
        res.setCharacterEncoding("UTF-8");// 设置将字符以"UTF-8"编码输出到客户端浏览器
        String name = req.getParameter("name");// 参数
        String pass = req.getParameter("pass");
        // getParameterValues用户获取数组
//        String[] insts = req.getParameterValues("describe");
        // 在服务器端使用getParameterNames方法接收表单参数
//        Enumeration<String> paramNames = req.getParameterNames();//获取所有的参数名
        // 在服务器端使用getParameterMap方法接收表单参数
//        Map<String, String[]> paramMap = req.getParameterMap();
    }
}
