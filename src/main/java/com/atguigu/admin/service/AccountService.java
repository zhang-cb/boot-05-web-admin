package com.atguigu.admin.service;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

public interface AccountService {

    public Account getAcct(Long id);

}
