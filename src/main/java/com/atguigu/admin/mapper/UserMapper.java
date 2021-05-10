package com.atguigu.admin.mapper;


import com.atguigu.admin.bean.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

//泛型为User,则默认回去数据库查找表名为user的表，如果数据库中的表名和实体Bean的名称有出入，可在Bean中设置一个数据库表名@TableName("user_tbl")
public interface UserMapper extends BaseMapper<User> {

}
