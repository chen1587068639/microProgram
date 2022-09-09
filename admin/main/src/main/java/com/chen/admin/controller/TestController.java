package com.chen.admin.controller;

import com.chen.admin.service.OrderService;
import com.chen.common.entity.Result;
import com.chen.common.vo.TestVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/admin")
public class TestController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/testAdmin")
    public Result<String> testAdmin() {
        log.info("执行testAdmin方法");
        return Result.success(orderService.callOrder());
    }

    @PostMapping("/validation")
    public TestVo testValidation(@RequestBody @Validated TestVo testVo) {
        log.info("TestVo:{}",testVo);
        return testVo;
    }
}
