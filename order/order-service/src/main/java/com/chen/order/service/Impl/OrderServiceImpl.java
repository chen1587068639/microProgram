package com.chen.order.service.Impl;

import com.chen.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public String orderTest() {
        log.info("如今orderTest方法");
        return "orderTest方法返回";
    }
}
