package com.wei.shopcart.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wei.service.bo.*;
import com.wei.service.service.CartService;
import com.wei.service.service.OrderService;
import com.wei.shopcart.aop.IsLogin;
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
@RequestMapping("/shop")
public class ShopcartController {
    @Reference
    private CartService cartService;
    @Reference
    private OrderService orderService;


    /**
     * 未登录添加redis,登录添加数据库
     *
     * @param cart
     * @param user
     * @param cartJson
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @IsLogin
    @RequestMapping("/addCart")
    @Transactional
    public String addCart(Cart cart, User user,
                          @RequestParam(value = "id", required = false) Integer id,
                          @CookieValue(name = "carts", required = false) String cartJson,
                          HttpServletRequest request,
                          HttpServletResponse response) throws UnsupportedEncodingException {
        if (user == null && id == null) {
            //未登录购物车信息保存在redis
            List<Cart> cartList = new ArrayList<>();
            List<Cart> carts = new ArrayList<>();
            carts.add(cart);
            //购物车有信息,累加到cookie
            if (cartJson != null && cartJson != "") {
                TypeToken<List<Cart>> typeToken = new TypeToken<List<Cart>>() {
                };
                cartList = new Gson().fromJson(cartJson, typeToken.getType());

                //商品id和数量添加到cookie
                carts.addAll(cartList);
                for (Cart cartBody : cartList) {
                    if (cartBody.getGoodId() != null && cartBody.getGoodId().equals(cart.getGoodId())) {
                        carts = new ArrayList<>();
                        Map<Integer, List<Cart>> cartsMap = cartList.stream().collect(Collectors.groupingBy(Cart::getGoodId));
                        Cart sameGoodIdcart = cartsMap.get(cart.getGoodId()).stream().findFirst().orElse(null);
                        sameGoodIdcart.setCount(sameGoodIdcart.getCount() + cart.getCount());
                        Set<Integer> goodIdS = cartsMap.keySet();
                        for (Integer goodId : goodIdS) {
                            List<Cart> cartS = cartsMap.get(goodId);
                            carts.addAll(cartS);
                        }
                        break;
                    }
                }
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
        } else {
            /*如果是模拟发送的http请求,即导入cookie的购物车到数据库*/
            if (id != null) {
                TypeToken<List<Cart>> typeToken = new TypeToken<List<Cart>>() {
                };
                List<Cart> cartList = new Gson().fromJson(cartJson, typeToken.getType());
                for (Cart cart1 : cartList) {
                    cart1.setUserid(id);
                    cartService.insert(cart1);
                    try {
                        response.getWriter().write("success");
                        return null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } else {
                System.out.println(user.toString());
                //登录则添加购物车到数据库
                cart.setUserid(user.getId());
                cartService.insert(cart);
            }

        }
        return "AddSuccess";
    }

    @RequestMapping("/cart")
    public Object cart(Model model) {
        return "Cart";
    }

    @IsLogin
    @RequestMapping("/queryCart")
    public Object queryCart(Model model, User user, @CookieValue(name = "carts", required = false) String carts) throws UnsupportedEncodingException {
        //未登录,去cookie查购物车
        if (user == null) {
            if (carts != null) {
                String cartJson = URLDecoder.decode(carts, "UTF-8");
                TypeToken<List<Cart>> typeToken = new TypeToken<List<Cart>>() {
                };
                List<Cart> cartList = new Gson().fromJson(cartJson, typeToken.getType());
                model.addAttribute("cartList", cartList);
            }
        } else {
            //已登录,数据库
            CartExample example = new CartExample();
            CartExample.Criteria criteria = example.createCriteria();
            criteria.andUseridEqualTo(user.getId());
            model.addAttribute("cartList", cartService.selectByExample(example));
        }
        return "CartList";
    }

    /**
     * 添加订单
     */
    @IsLogin
    @RequestMapping("orderList")
    public Object orderList(Model model,User user) {
        if(user == null){
            return "redirect:http://localhost:8084/userRedis/lgin";
        }
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
