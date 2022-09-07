package com.chen.order.controller;

import com.chen.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/area")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderRequest")
    public String firstRequest(){
        log.info("进入firstRequest方法");
        return orderService.orderTest();
    }
}
