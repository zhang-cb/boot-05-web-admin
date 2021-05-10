package com.atguigu.admin.excepetion;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN,reason = "用户数量太多")
public class UserTOOManyExeption extends RuntimeException{

    public UserTOOManyExeption(){

    }

    public UserTOOManyExeption(String message){
        super(message);
    }
}
