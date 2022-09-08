package com.chen.admin.service.Impl;

import com.chen.admin.service.OrderService;
import com.chen.order.api.AdminApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

//    @NacosInjected
//    private AdminApi adminApi;

    public String callOrder(){
        log.info("执行callOrder方法");
        return "adminApi.testMethod()";
    }
}
