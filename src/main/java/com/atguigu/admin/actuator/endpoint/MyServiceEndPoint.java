package com.atguigu.admin.actuator.endpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * 场景：开发ReadinessEndpoint来管理程序是否就绪，或者LivenessEndpoint来管理程序是否存活；
 * 当然，这个也可以直接使用 https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features
 * .html#production-ready-kubernetes-probes
 */
@Component
@Endpoint(id = "myServiceEndPoint")
public class MyServiceEndPoint {

    @ReadOperation
    public Map getDockerInfo(){
        //端点的读操作，打开http://localhost:8888/actuator/myServiceEndPoint能看到相关数据
        return Collections.singletonMap("info","docker started...");
    }

    @WriteOperation
    private void restartDocker(){
        System.out.println("docker restarted....");
    }
}
