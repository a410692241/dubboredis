package com.wei.service.aop;

import com.wei.service.bo.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 务必要用spring管理,添加扫描
 */
@Aspect
@Order(200)
public class MustLoginAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisTemplate redisTemplate;


    @Around("execution(* *..*controller.*.*(..)) && @annotation(com.wei.service.aop.MustLogin)")
    public Object mustLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("-----------------登录跳转验证开始--------------------");
        Cookie[] cookies = request.getCookies();
        User user = null;
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String value = cookie.getValue();
                    user = (User) redisTemplate.opsForValue().get(value);
                    break;
                }

            }
        }

        //表示已经登录
        if(user == null){
            String queryString = request.getQueryString();
            if (queryString == null || queryString == "null") {
                queryString = "";
            }
            String fullUrl = request.getScheme() + "://" + request.getServerName() +":"+request.getServerPort() + request.getServletPath()+ queryString;
            return "redirect:http://localhost:8084/userRedis/lgin?lookUrl=" + fullUrl;
        }
        return proceedingJoinPoint.proceed();
    }
}
