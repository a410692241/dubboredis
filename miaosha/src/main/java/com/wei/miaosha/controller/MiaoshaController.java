package com.wei.miaosha.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wei.service.bo.Miaosha;
import com.wei.service.bo.Order;
import com.wei.service.service.MiaoshaService;
import com.wei.service.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("miaosha")
public class MiaoshaController {
    @Reference
    private MiaoshaService miaoshaService;
    @Reference
    private OrderService orderService;




    @RequestMapping("addOrder")
    @ResponseBody
    public synchronized void addOrder() {
        for (int i = 0; i < 10000; i++) {
            new Thread() {
                @Override
                public void run() {
                    Order order = orderService.selectByPrimaryKey(14);
                    Integer count = order.getCount();
                    if (count > 0) {
                        order.setCount(count - 1);
                        orderService.updateByPrimaryKey(order);
                        Miaosha record = new Miaosha();
                        miaoshaService.insert(record);
                    }
                }
            }.start();
        }

    }


    @RequestMapping("redisMiaosha")
    @ResponseBody
    public void redisMiaosha() {
        miaoshaService.redisMiaosha();
    }


}
