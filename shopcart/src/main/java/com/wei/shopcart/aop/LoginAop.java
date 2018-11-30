package com.wei.shopcart.aop;

import com.wei.service.bo.User;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 务必要用spring管理,添加扫描
 */
@Aspect
@Component
public class LoginAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisTemplate redisTemplate;

    /**进行环绕增强,对比@before和@after在方法前可以可以加判断,放行后也可以加判断
     * 增强任何返回值在这个包下的任何类的方法,不管它有任何参数并且得有islogin注解
     */
    @Around("execution(* com.wei.shopcart.controller.*.*(..)) && @annotation(com.wei.shopcart.aop.IsLogin)")
    public String isLogin(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("-----------------登录验证开始--------------------");
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
        //获取拦截的方法参数,如果有该类型的参数,就将该user的对象引用赋值给拦截的方法参数
        Object[] args = proceedingJoinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof User) {
                args[i] = user;
            }

        }
        //proceedingJoinPoint.proceed(args);这样写会把springMVC的视图渲染器给截掉,返回不了页面,所以用下面的方式
        //下面的写法说明aop支持返回拦截
        return (String) proceedingJoinPoint.proceed(args);


    }
}
