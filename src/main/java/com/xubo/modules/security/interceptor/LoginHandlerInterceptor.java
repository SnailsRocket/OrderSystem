package com.xubo.modules.security.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author Druid_Xu
 * @Date 2020/12/9 下午 03:33
 * @Description
 * 这个只是写登录拦截，最后再写登录模块，先把此块注释掉
 * 这个Bean 需要add 到 InterceptorRegistry 这个拦截器注册登记类里面(在继承
 *      webMvcConfigurerAdapter 的类里面实现的)
 */
@Component
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行之前
    /*@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 假设登录信息存放在session里面
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser != null) {
            // 已登录
            return true;
        }
        // 未经过验证
        //设置返回值信息
        request.setAttribute("msg","没权限请先登录");
        // 跳转到登录页面，并且需要携带request response  redirect 重定向  forward 转发
        request.getRequestDispatcher("./login.html").forward(request,response);
        return false;
    }

    //目标方法执行过程中
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    //目标方法执行之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }*/
}
