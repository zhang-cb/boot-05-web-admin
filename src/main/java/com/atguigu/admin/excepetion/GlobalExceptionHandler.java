package com.atguigu.admin.excepetion;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *  处理整个Web controller的异常
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({ArithmeticException.class,NullPointerException.class})     //异常处理器
    public String handleArithException(Exception e){

        log.error("异常是:{}",e);
        return "login";//视图地址
    }
}
