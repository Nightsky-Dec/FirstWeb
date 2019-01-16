package com.example.demo.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装的用户注册表单bean，用来接收register.jsp中的表单输入项的值
 * RegisterFormBean中的属性与register.jsp中的表单输入项的name一一对应
 * RegisterFormBean的职责除了负责接收register.jsp中的表单输入项的值之外还担任着校验表单输入项的值的合法性
 * @author gacl
 */
public class LoginUser {
    private int uid;
    private String name;
    private String pass;
    private String confPass;
    private String email;
    private String age;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 存储校验不通过时给用户的错误提示信息
     * */
    private Map<String, Object> errors = new HashMap<String, Object>();

    public Map<String, Object> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, Object> errors) {
        this.errors = errors;
    }

    /**
     * validate方法负责校验表单输出项
     * 表单输入项校验规则：
     * private String name：用户名不能为空，并且要是3-8的字母 admin
     * private String pass：密码不能为空，并且要是3-8位数字
     * private String confPass：两次密码要一致
     * private String email：可以为空，不为空要是一个合法的邮箱
     * private String birthday：可以为空，不为空时，要是一个合法的日期
     * */
    public boolean validate() {
        boolean isOk = true;

        if (this.name == null || this.name.trim().equals("")) {
            isOk = false;
            errors.put("name","用户名不能为空！！");
        } else {
            if (!this.name.matches("[a-zA-Z]{3,8}")) {
                isOk = false;
                errors.put("name", "用户名必须是3-8位的字母！！");
            }
        }

        if (this.pass == null || this.pass.trim().equals("")) {
            isOk = false;
            errors.put("pass", "密码不能为!!");
        } else {
            if (!this.pass.matches("\\d{3,8}")) {
                isOk = false;
                errors.put("pass", "密码必须是3-8位的数字！！");
            }
        }

        // private String confPass；两次密码要一致
        if (this.confPass != null) {
            if (!this.confPass.equals(this.pass)) {
                isOk = false;
                errors.put("confirmPass", "两次密码不一致！！");
            }
        }

        // private String email；可以为空，不为空时，要是一个合法的邮箱
        if (this.email != null && !this.email.trim().equals("")) {
            if (!this.email.matches("\\w+@\\w+(\\.\\w+)+")) {
                isOk = false;
                errors.put("email", "邮箱不是一个合法邮箱！！");
            }
        }

        return isOk;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getConfPass() {
        return confPass;
    }

    public void setConfPass(String confPass) {
        this.confPass = confPass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        String info = "User【";
        if (uid != 0) {
            info += "uid=" + uid + ";";
        }
        if (name != null) {
            info += "name=" + name + ";";
        }
        if (pass != null) {
            info += "pass=" + pass + ";";
        }
        if (email != null) {
            info += "email=" + email + ";";
        }
        if (age != null) {
            info += "age=" + age + ";";
        }
        info += "】";
        return info;
    }
}
