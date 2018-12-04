package com.wei.pay.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wei.service.aop.IsLogin;
import com.wei.service.aop.MustLogin;
import com.wei.service.service.CartService;
import com.wei.service.service.OrderService;
import com.wei.service.util.PayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Reference
    private CartService cartService;
    @Reference
    private OrderService orderService;


    /**
     * 添加订单
     */
    @IsLogin
    @MustLogin
    @RequestMapping("payList")
    public Object orderList(Model model, @RequestParam("goodIds") List<String> goodIds, @RequestParam("counts") List<String> counts) {
        int total = 0;
        for (int i = 0; i < goodIds.size(); i++) {
            int id = Integer.parseInt(goodIds.get(i));
            int count = Integer.parseInt(counts.get(i));
            total += count * id;
        }
        model.addAttribute("total_amount", total);
        model.addAttribute("orderId", 1);
        return "payList";
    }

    @IsLogin
    @MustLogin
    @RequestMapping("addPay")
    public void addPay(@RequestParam("totalAmount") double totalAmountl, @RequestParam("orderId") String orderId,HttpServletResponse response) {
        String result = PayUtil.pay(orderId, totalAmountl, "在线商城支付", "http://localhost:8084/userRedis/");
        PrintWriter writer = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(result);

    }

    @RequestMapping("loadPay")
    public Object payResult(String orderId,Model model) {
        model.addAttribute("orderId", orderId);
        return "loadPay";
    }

    @RequestMapping("getPayResult")
    @ResponseBody
    public Object getPayResult(@RequestParam("orderId") String orderId) {
        return PayUtil.getPayResult(orderId);
    }

    @RequestMapping("paySuccess")
    public Object paySuccess() {
        return "paySuccess";
    }




}
