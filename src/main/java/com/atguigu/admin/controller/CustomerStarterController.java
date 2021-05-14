package com.atguigu.admin.controller;

import com.atguigu.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerStarterController {
    @Autowired
    HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello(){
       return helloService.sayHello("张三");
    }
}
