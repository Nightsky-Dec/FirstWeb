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
     * 用于用户认证校验、用户权限校验
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
     * 在执行handler返回modelAndView之前来执行
     * 如果需要向页面提供一些公用 的数据或配置一些视图信息，使用此方法实现 从modelAndView入手
     * 本函数的作用:计算处理时间
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    /**
     * 执行handler之后执行此方法
     * 作系统 统一异常处理，进行方法执行性能监控，在preHandle中设置一个时间点，在afterCompletion设置一个时间，两个时间点的差就是执行时长
     * 实现 系统 统一日志记录
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }
}
