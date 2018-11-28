package com.wei.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wei.service.bo.User;
import com.wei.service.bo.UserExample;
import com.wei.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 最基础的redis实现方法和springboot下的redis缓存使用方法
 * @author user
 */
@Controller
@RequestMapping("/userRedis")
public class UserRedisController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Reference
    private UserService userService;

    @RequestMapping("/AddUser")
    public Object AddUser(Model model) {
        return "AddUser";
    }

    @RequestMapping("/List")
    public Object List(Model model) {
        List<User> users = userService.selectByExample(new UserExample());
        model.addAttribute("userList", users);
        return "List";
    }
    @RequestMapping("/")
    public Object All(Model model) {
        List<User> users = userService.selectByExample(new UserExample());
        model.addAttribute("userList", users);
        return "Index";
    }


    @RequestMapping("/index")
    public Object index(Model model) {
        List<User> users = userService.selectByExample(new UserExample());
        model.addAttribute("userList", users);
        return "AddUser";
    }

    /**多用于查询业务,如果该查询结果能在缓存服务器查询,如果返回服务器没该数据,则调用标记方法,根据方法返回值进行缓存的重建
     * @param id
     * @return
     */
    @RequestMapping("/cacheGet")
    @ResponseBody
    public Object cacheGet(int id) {
        User user = userService.selectByPrimaryKey(id);
        return user;
    }



    /**CachePut用于更新数据时候缓存进行更新
     * @param user
     * @return
     */
    @RequestMapping("/saveUser")
    public Object updateUser(User user) {
        if (user.getId() != null) {
             userService.updateByPrimaryKey(user);
        }else {
             userService.insert(user);
        }
        return "List";
    }



    /**CacheEvict用于更新数据时候缓存进行更新
     * @param id
     * @return
     */

    @RequestMapping("/delUser")
    public Object delUser(int id) {
        userService.deleteByPrimaryKey(id);
        return "List";
    }

    @RequestMapping("/onless100")
    @ResponseBody
    public Object onless100(int id) {
        return userService.unless100(id);
    }


    @RequestMapping("/lgin")
    public Object lgin(Model model,String lookUrl) {
        model.addAttribute("lookUrl", lookUrl);
        return "Login";
    }




}
