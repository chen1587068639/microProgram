package com.chen.admin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/admin")
public class TestController {

    public String testAdmin(){
        log.info("执行testAdmin方法");
        return "return admin";
    }
}
