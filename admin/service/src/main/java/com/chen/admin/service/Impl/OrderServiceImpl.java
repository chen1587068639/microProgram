package com.chen.admin.service.Impl;

import com.chen.admin.service.OrderService;
import com.chen.order.api.AdminApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

//    @DubboReference
//    private AdminApi adminApi;

    public String callOrder(){
        log.info("执行callOrder方法");
//        //远程调用order模块接口
//        return adminApi.testMethod();
        return "callOrder";
    }
}
