package com.wei.shopcart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wei.service.aop.IsLogin;
import com.wei.service.aop.MustLogin;
import com.wei.service.bo.CartExample;
import com.wei.service.bo.Order;
import com.wei.service.bo.OrderExample;
import com.wei.service.bo.User;
import com.wei.service.service.CartService;
import com.wei.service.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

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
    @Transactional
    public Object addOrder(@RequestParam("goodIds") List<String> goodIdsList,User user,Model model) {
        //未登录
        List<String> goodIdList = new ArrayList<>();
        for (String goodIds : goodIdsList) {
            String[] goodIdArr = goodIds.split(";");
            goodIdList.add(goodIdArr[0]);
            int goodId = Integer.parseInt(goodIdArr[0]);

            int count = Integer.parseInt(goodIdArr[1]);
            Order order = new Order();
            order.setGoodid(goodId);
            order.setUserid(user.getId());
            order.setCount(count);
            orderService.insert(order);
            CartExample example = new CartExample();
            CartExample.Criteria criteria = example.createCriteria();
            criteria.andUseridEqualTo(user.getId());
            criteria.andGoodidIn(goodIdList);
            cartService.deleteByExample(example);


        }
        //订单添加商品id
        return "redirect:http://localhost:8084/userRedis/";
    }





}
