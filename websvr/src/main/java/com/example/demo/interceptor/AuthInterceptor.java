package com.example.demo.interceptor;

import com.example.demo.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthInterceptor implements HandlerInterceptor {
    // 注入redis
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 在视图函数之前执行
     * 返回true, 继续执行视图函数
     * 返回false, 终止请求流程
     * 本函数的作用,:拒绝特定时间sentinel:forbidden:hours; 特定用户的sentinel:forbidden:users请求, 并记录startTime
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String OPTIONS = request.getMethod();
        // 前端axios一些情况下会传一个OPTIONS测试与后端是否联通，此请求不需要任何实际操作
        if (OPTIONS.equals("OPTIONS")) { return false; }

        // 获取URL
        String url = request.getRequestURI();
        // URL：登录/登出接口不需要Token认证
        if (url.indexOf("/user/login") >= 0 || url.indexOf("/user/logout") >=0) { return true; }

        /**
         * Token认证
         * 判断redis中是否存在此token
         * */
        String token = request.getHeader("x-auth-token");
        if (token!=null && redisUtils.hasKey(token)) {
            return true;
        }

        return false;
    }

    /**
     * 在视图函数之后执行，ModelAndView返回之前调用这个方法
     * 本函数的作用:计算处理时间
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 视图函数执行成功后执行
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
