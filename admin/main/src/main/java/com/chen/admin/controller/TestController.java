package com.chen.admin.controller;

import com.chen.admin.service.OrderService;
import com.chen.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class TestController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/testAdmin")
    public Result<Integer> testAdmin() {
        log.info("执行testAdmin方法");
        int i = 10/0;
        return Result.success(i);
    }
}
