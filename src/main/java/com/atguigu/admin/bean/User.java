package com.atguigu.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
//@TableName("user_tbl")
public class User {

    //使用mybatisPlus的BaseMapper里面的方法去查询时，所有属性都应该在数据库中，如果只是单纯想要属性有，但数据库没有，可以用以下的注解
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String password;

//    以下是数据库的字段
private Long id;
    private String name;
    private Integer age;
    private String email;
}
