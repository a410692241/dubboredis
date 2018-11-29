package com.wei.shopcart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wei.service.bo.Cart;
import com.wei.service.bo.User;
import com.wei.service.service.CartService;
import com.wei.shopcart.aop.IsLogin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopcartController {
    @Reference
    private CartService cartService;


    @IsLogin
    @RequestMapping("/addCart")
    public String addCart(Cart cart, User user,
                          @CookieValue(name = "carts",required = false) String cartJson,
                          HttpServletResponse response) throws UnsupportedEncodingException {
        System.out.println(user.toString());

        if (user == null) {
            //未登录
            return "redirect:http://localhost:8084/userRedis/lgin";
        } else {
            List<Cart> carts = new ArrayList<>();
            //购物车有信息,累加到cookie
            if (cartJson != null && cartJson != "") {
                TypeToken<List<Cart>> typeToken = new TypeToken<List<Cart>>() {};
                List<Cart> cartList = new Gson().fromJson(cartJson, typeToken.getType());

                carts.addAll(cartList);
            } else {
                //商品id和数量添加到cookie
                carts = Collections.singletonList(cart);
            }
            /*这里务必再对cookie进行转码,因为cookie中的字符是ASCII码 ,
             *""对应的是34,json字符串充斥大量的"号,所以会报错:Cookie值中存在无效字符[34]
             * 结果是%5B%7B%22goodId%22%3A1%2C%22count%22%3A32%7D%5D
             */
            String encodeValue = URLEncoder.encode(new Gson().toJson(carts), "UTF-8");

            Cookie cookie = new Cookie("carts", encodeValue);
            cookie.setPath("/");
            //一个月
            cookie.setMaxAge(30 * 24 * 60 * 60);
            response.addCookie(cookie);

            //登录添加购物车到数据库
            cart.setUserid(user.getId());
            cartService.insert(cart);


        }

        return "AddSuccess";
    }

    @RequestMapping("/cart")
    public Object cart(Model model) {
        return "Cart";
    }

}
