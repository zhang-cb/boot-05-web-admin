package com.atguigu.admin;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.atguigu.admin.mapper")  //若配置了这个，会自动扫描该包下的java文件，识别为mapper,则下面的文件就不需要每个类都写@mapper标注
@ServletComponentScan("com.atguigu.admin")   //要用原生注解@WebServlet/@WebListener/@WebFilter  则必须加入扫描，否则这些注解的功能不会生效
@SpringBootApplication(exclude = RedisAutoConfiguration.class)  //排除RedisAutoConfiguration
public class Boot05WebAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot05WebAdminApplication.class, args);
    }

}
