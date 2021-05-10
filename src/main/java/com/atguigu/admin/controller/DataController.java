package com.atguigu.admin.controller;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.bean.City;
import com.atguigu.admin.service.AccountService;
import com.atguigu.admin.service.CityService;
import com.atguigu.admin.service.impl.AccountServiceImpl;
import com.atguigu.admin.service.impl.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DataController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @ResponseBody
    @GetMapping("/sql")
    public String queryFormDb(){
        Long size = jdbcTemplate.queryForObject("select count(*) from account_tbl",long.class);
        return size.toString();
    }

    @Autowired
    AccountService accountService;


    @ResponseBody
    @GetMapping("/getAcctId")
    public Account getAcctId(@RequestParam("id") Long id){
        Account account = accountService.getAcct(id);
        return account;
    }

    @Autowired
    CityService cityService;

    @ResponseBody
    @GetMapping("/getCityById")
    public City getCityById(@RequestParam("id") Long id){
        City city = cityService.getCityById(id);
        return city;
    }

    //用postman发送post请求
    @ResponseBody
    @PostMapping("/saveCity")
    public City saveCity(City city){
        cityService.saveCity(city);
        return city;
    }

}
