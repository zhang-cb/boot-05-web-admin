package com.atguigu.admin.controller;

import com.atguigu.admin.bean.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
public class IndexController {

    //@Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping(value = {"/","/login"})
    public String loginPage(){

        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){

        if("123".equals(user.getPassword())&& StringUtils.hasLength(user.getUserName())){
            //把登录成功的信息保存起来
            session.setAttribute("loginUser",user);
            //登录成功重定向到/main.html，如果校验完直接跳转到main为转发，会有数据重复提交的问题，地址栏的地址也会发生变化，刷新的时候就不会重复提交
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","账号密码错误");
            //回到登录页面
            return "login";
        }


    }


    /**
     * 去main页面，防止表单重复提交，采用重定向
     * @return
     */
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
        log.info("当前方法是{}","mainPage");

        //是否登录   拦截器，过滤器
       Object loginUser = session.getAttribute("loginUser");
        if(loginUser!=null){
            return "main";
        }else{
            //回到登录页
            model.addAttribute("msg","请重新登录");
            return "login";
        }

        //ValueOperations<String, String> operations = redisTemplate.opsForValue();
        //String s = operations.get("/main.html");
        //String s1 = operations.get("/sql");
        //
        //model.addAttribute("mainCount",s);
        //model.addAttribute("sqlCount",s1);


        //return "main";

    }

}
