package com.wei.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wei.service.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class Usercontroller {
    @Reference
    private EmployeeService employeeService;

    @RequestMapping("hello")
    public Object hello() {
        return employeeService.selectByPrimaryKey(65);
    }

}
