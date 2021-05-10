package com.atguigu.admin.servlet;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Arrays;


//(proxyBeanMethods = true)：  保证依赖的组件始终是单实例的，但是每次都要去容器查找组件，比较损耗效率
@Configuration
public class MyRegistConfig {

    @Bean
    public ServletRegistrationBean myServlet(){
        MyServlet myServlet = new MyServlet();
        return new ServletRegistrationBean(myServlet,"/myServlet");
    }

    @Bean
    public FilterRegistrationBean myFilter(){
        MyFilter myFilter = new MyFilter();

        //return new FilterRegistrationBean(myFilter, myServlet());
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>(myFilter);
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/css/*"));
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean myListener(){
        MyListener myListener = new MyListener();
        return  new ServletListenerRegistrationBean(myListener);
    }
}
