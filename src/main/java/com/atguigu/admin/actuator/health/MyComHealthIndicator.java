package com.atguigu.admin.actuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 端点health：显示应用程序运行状况信息。
 *
 * 监控平台health处显示的名称为:myCom
 */
@Component
public class MyComHealthIndicator extends AbstractHealthIndicator {

    /**
     * 真实的检查方法
     * @param builder
     * @throws Exception
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        //mongdb,  获取连接进行测试
        HashMap<String, Object> map = new HashMap<>();
        if (1==1) {
            //builder.up();  //健康
            builder.status(Status.UP);
            map.put("count",1);
            map.put("ms",100);
        }else {
            builder.down();
            map.put("err","连接超时");
            map.put("ms",3000);
        }

        builder.withDetail("code",200).withDetails(map);


    }


}
