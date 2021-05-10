package com.atguigu.admin.service.impl;

import com.atguigu.admin.bean.Account;
import com.atguigu.admin.bean.City;
import com.atguigu.admin.mapper.AccountMapper;
import com.atguigu.admin.mapper.CityMapper;
import com.atguigu.admin.service.CityService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    CityMapper cityMapper;

    //方式一：：用actuator监控平台监控saveCity的调用次数
    Counter counter;

    public CityServiceImpl(MeterRegistry meterRegistry){
        counter = meterRegistry.counter("cityService.saveCity.count");
    }


    public City getCityById(Long id){
        return cityMapper.findById(id);
    }

    public void saveCity(City city) {
        counter.increment();
        //使用mapper.xml方式的↓
        //cityMapper.insertCity(city);
        //使用注解方式↓
        cityMapper.insert(city);
    }
}
