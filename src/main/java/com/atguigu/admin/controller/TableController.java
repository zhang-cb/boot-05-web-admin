package com.atguigu.admin.controller;

import com.atguigu.admin.bean.User;
import com.atguigu.admin.excepetion.UserTOOManyExeption;
import com.atguigu.admin.service.UserService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.List;

@Controller
public class TableController {

    /**
     * 访问时，不带请求参数或参数类型不对，会抛400异常：Bad Request 一般是浏览器的参数没有传递正确，因为/error的只有404.html，异常机制是精确按状态码查找页面，只响应404
     * 异常，故需要将页面名改为4xx.html，响应所有4状态码开头的异常
     * @param a
     * @return
     */
    @GetMapping("/basic_table")
    public String basic_table(@RequestParam("a") int a){
        //异常跳转到5xx页面打印异常信息
        int i = 10/0;

        return "table/basic_table";
    }

    @Autowired
    UserService userService;

    //表格内容的遍历
    @GetMapping("/dynamic_table")
    public String dynamic_table(@RequestParam(value = "pn",defaultValue = "1")Integer pn, Model model){
        //List<User> userList = Arrays.asList(new User("zhangsan", "123"),
        //        new User("lisi", "123"),
        //        new User("wangwu", "123"),
        //        new User("yuziqi", "123"));

        //model.addAttribute("userList",userList);

        //if(userList.size()>3){
        //    throw new UserTOOManyExeption();
        //}


        //以下打通mybatisPlus通过数据库查找数据，显示在页面
        List<User> list = userService.list();
        //model.addAttribute("userList",list);

        //分页查询数据
        Page<User> userPage = new Page<User>(pn,2);
        //分页查询的结果
        Page<User> page = userService.page(userPage, null);

        model.addAttribute("page",page);


        return "table/dynamic_table";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable(value = "id")Long id, @RequestParam(value = "pn",defaultValue = "1")Integer pn, RedirectAttributes ra){
      userService.removeById(id);

      ra.addAttribute("pn",pn);
        return "redirect:/dynamic_table";
    }

    @GetMapping("/responsive_table")
    public String responsive_table(){
        return "table/responsive_table";
    }

    @GetMapping("/editable_table")
    public String editable_table(){
        return "table/editable_table";
    }



}
