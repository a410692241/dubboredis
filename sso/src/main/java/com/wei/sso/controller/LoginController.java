package com.wei.sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.wei.service.bo.User;
import com.wei.service.bo.UserExample;
import com.wei.service.service.UserService;
import com.wei.service.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * cookie跨域测试
 * @author user
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Reference
    private UserService userService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("getCookie")
    @ResponseBody
    public Object getCookie(@CookieValue("token") String token) {
        return token;
    }

    /**
     * @param user
     * @param response
     * @param lookUrl 点击登录前的页面,用于登录成功之后跳转回对应的页面
     * @return
     */
    @RequestMapping("/login")
    public Object login(User user, HttpServletResponse response,String lookUrl,
                        @CookieValue(value = "carts",required = false) String carts) throws UnsupportedEncodingException {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(user.getUsername());
        User userRS = userService.selectByExample(userExample).stream().findFirst().orElse(null);
        if (userRS.getPassword().equals(user.getPassword())) {
            String uuid = UUID.randomUUID().toString();
            redisTemplate.opsForValue().set(uuid,userRS);
            redisTemplate.expire(uuid, 7, TimeUnit.DAYS);
            Cookie cookie = new Cookie("token",uuid);
            //7天有效
            cookie.setMaxAge(7 * 24 * 60 * 60);
            //设置host
//            cookie.setDomain("localhost");
            cookie.setPath("/");
            //可能会有跨域的问题,为了防止银行的cookie被其他地址使用,所有本项目的cookie才能本项目使用,所以要设置setPath("/");
//            cookie.setPath("http://localhost:8084/userRedis/");
            response.addCookie(cookie);

            //合并购物车
            if (carts != null && carts != "") {
                Map<String, String> headMap = new HashMap<>();
                headMap.put("cookie", "carts="+ URLEncoder.encode(carts,"UTF-8"));
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("id", userRS.getId()+"");
                String responseData = HttpClientUtil.doPost("http://localhost:8088/shop/addCart", paramMap, headMap);
                if ("success".equals(responseData)) {
                    //删除cookie,需要指定cookie的路径
                    Cookie cartsCookie = new Cookie("carts", "");
                    cartsCookie.setPath("/");
                    //删除
                    cartsCookie.setMaxAge(0);
                    response.addCookie(cartsCookie);
                }
            }
        }
        //前端对url进行&转*,后台转回来,防止在login方法开始的时候就把http://localhost:8085/login/login?lookUrl = http://localhost:8084/userRedis/?id=1&name=2
        //中的lookUrl = http://localhost:8084/userRedis/?id=1当成一个参数name当做第二个参数
        lookUrl = lookUrl.replace("*", "&");
        System.out.println(lookUrl);
        if (lookUrl == null || "".equals(lookUrl)) {
            lookUrl = "";
    }
        return "redirect:"+lookUrl;
    }


    /**CookieValue等同于
     Cookie[] cookies = request.getCookies();
     for (Cookie cookie : cookies) {
         if (cookie.getName().equals("token")) {
             String value = cookie.getValue();
             break;
         }
     }
     required = false表示该参数并不是必须,没有传值的时候为null,如果required = true,表示当该参数没传值会访问不到接口

     * @param TokenValue
     * @return
     */
    @RequestMapping("/isLogin")
    @ResponseBody
    public Object isLogin(@CookieValue(value = "token",required = false) String TokenValue,String lookUrl) {
        User user = null;
        if (TokenValue != null) {
            user = (User) redisTemplate.opsForValue().get(TokenValue);
        }
        if (lookUrl != null) {

        }
        return "callback("+new Gson().toJson(user)+")";
    }


    /**登出
     * @param TokenValue
     * @param response
     * @return
     */
    @RequestMapping("/logout")
    public Object logout(@CookieValue(value = "token",required = false) String TokenValue, HttpServletResponse response) {
        //删除cookie的uuid
        //删除redis的uuid
        redisTemplate.delete(TokenValue);
        Cookie token = new Cookie("token","");
        token.setPath("/");
        token.setMaxAge(0);
        response.addCookie(token);
        return "redirect:http://localhost:8084/userRedis/";
    }



}
