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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/pay")
public class PayController {
    @Reference
    private CartService cartService;
    @Reference
    private OrderService orderService;


    /**
     * 添加订单
     *
     * @param model
     * @param goodIds
     * @param counts
     * @return
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


    /**添加支付宝支付
     * @param totalAmountl
     * @param orderId
     * @param response
     */
    @IsLogin
    @MustLogin
    @RequestMapping("addPay")
    public void addPay(@RequestParam("totalAmount") double totalAmountl, @RequestParam("orderId") String orderId, HttpServletResponse response) {
        String result = PayUtil.pay(orderId, totalAmountl, "在线商城支付", "http://229t2351e9.51mypc.cn:23540/userRedis/","http://229t2351e9.51mypc.cn:23540/pay/message");
        PrintWriter writer = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            writer = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.write(result);

    }

    /**
     * @param orderId
     * @param model
     * @return等待支付界面
     */
    @RequestMapping("loadPay")
    public Object payResult(String orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "loadPay";
    }




    /**
     * @param orderId
     * @return根据订单号获取支付结果
     */
    @RequestMapping("getPayResult")
    @ResponseBody
    public Object getPayResult(@RequestParam("orderId") String orderId) {
        return PayUtil.getPayResult(orderId);
    }




    /**
     * @return支付成功
     */
    @RequestMapping("paySuccess")
    public Object paySuccess() {
        return "paySuccess";
    }


    /**
     * @return支付成功异步通知
     */
    @RequestMapping("message")
    @ResponseBody
    public void message() {
        System.out.println("支付成功异步通知");
    }


}
