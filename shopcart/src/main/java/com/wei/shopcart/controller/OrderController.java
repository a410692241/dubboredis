package com.wei.shopcart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wei.service.bo.*;
import com.wei.service.service.CartService;
import com.wei.service.service.OrderService;
import com.wei.shopcart.aop.IsLogin;
import com.wei.shopcart.aop.MustLogin;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Reference
    private CartService cartService;
    @Reference
    private OrderService orderService;


    /**
     * 添加订单
     */
    @IsLogin
    @MustLogin
    @RequestMapping("orderList")
    public Object orderList(Model model,User user) {
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(user.getId());
        List<Order> orders = orderService.selectByExample(example);
        model.addAttribute("orderList", orders);
        //订单添加商品id
        return "OrderList";
    }

    /**
     * 添加订单
     * @param goodIdsList
     * @return
     */

    @IsLogin
    @MustLogin
    @RequestMapping("addOrder")
    public Object addOrder(@RequestParam("goodIds") List<String> goodIdsList,User user,Model model) {
        //未登录
        for (String goodIds : goodIdsList) {
            String[] goodIdArr = goodIds.split(";");
            int goodId = Integer.parseInt(goodIdArr[0]);
            int count = Integer.parseInt(goodIdArr[1]);
            Order order = new Order();
            order.setGoodid(goodId);
            order.setUserid(user.getId());
            order.setCount(count);
            orderService.insert(order);
        }
        //订单添加商品id
        return "redirect:http://localhost:8084/userRedis/";
    }
}
