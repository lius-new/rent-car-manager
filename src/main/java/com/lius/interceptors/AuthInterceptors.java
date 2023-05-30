package com.lius.interceptors;


import com.lius.common.Utils;
import com.lius.entity.User;
import com.lius.service.UserService;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.context.support.UiApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@Component
public class AuthInterceptors implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    //
    private boolean ignoreMethodAndUri(HttpServletRequest request) {
        HashMap<String, String> ignoreMethodAndUris = new HashMap<>();
        ignoreMethodAndUris.put("/car","GET");
        ignoreMethodAndUris.put("/car/car-user-rent","GET");

        String method = request.getMethod();
        String requestURI = request.getRequestURI();

        if (ignoreMethodAndUris.containsKey(requestURI)){
            return ignoreMethodAndUris.get(requestURI).equals(method);
        }
        return false;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 过滤OPTIONS请求，该请求用于验证请求是否安全
        if ("OPTIONS".equals(request.getMethod())) return true;

        // 被过滤的请求
        if (ignoreMethodAndUri(request)) return true;

        String token = request.getHeader("Authorization");

        try{
            User user = Utils.parseToken(token);
            return userService.checkLogin(user);
        }catch (IllegalArgumentException e){
            throw  new JwtException("token 为空");
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
