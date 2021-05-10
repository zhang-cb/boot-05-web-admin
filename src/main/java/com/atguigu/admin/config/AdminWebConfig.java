package com.atguigu.admin.config;

import com.atguigu.admin.interceptor.LoginInterceptor;
import com.atguigu.admin.interceptor.RedisUrlCountInterceptor;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Queue;

/**
 * 1、编写一个拦截器，实现HandlerInterceptor接口
 * 2、拦截器注册到容器中（实现WebMvcConfigurer接口并重写addInterceptors方法）
 * 3、指定拦截器规则【如果是拦截所有，静态资源也会被拦截】
 *
 * @EnableWebMvc ： 全面接管
 *      1、静态资源、欢迎页、视图解析器 等官方配置好的解析器、处理器都会失效，所有规则全部自己重新配置；实现定制和扩展功能
 */
//@EnableWebMvc
@Configuration
public class AdminWebConfig implements WebMvcConfigurer {

    /**
     * Filter、Interceptor 几乎拥有相同的功能
     * 1、Filter是Servlet的原生组件，好处：脱离了Spring应用也能使用
     * 2、Interceptor是Spring定义的接口，可以使用Spring的自动装配@Autowired
     */
    //@Autowired
    RedisUrlCountInterceptor redisUrlCountInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器，并配置拦截请求的路径与不拦截的路径
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")    //所有请求都会被拦截，包括静态资源
                .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**","/aa/**","/saveCity"); //放行的请求

        //增加Redis的uri计数拦截器
        //registry.addInterceptor(redisUrlCountInterceptor)
        //        .addPathPatterns("/**")
        //        .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**", "/js/**","/aa/**","/saveCity");
    }


    /**
     * 定义静态资源行为
     * @param registry
     */
  /*  @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        *//**
         * 访问 /aa/** 的所有请求，都去 classpath:/static/ 下面去进行匹配
         * /aa/css/bootstrap.min.css 会去 /static/css/bootstrap.min.css下找
         *//*
        registry.addResourceHandler("/aa/**").addResourceLocations("classpath:/static/");

    }*/


    //方式二：：自定义metrics指标项
    @Bean
    MeterBinder queueSize(Queue queue) {
        return (registry) -> Gauge.builder("queueSize", queue::size).register(registry);
    }



}
