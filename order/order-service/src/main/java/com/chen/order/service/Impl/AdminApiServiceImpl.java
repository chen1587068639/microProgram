package com.chen.order.service.Impl;

import com.chen.order.api.AdminApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AdminApiServiceImpl implements AdminApi {

    @Override
    public String testMethod() {
        log.info("进入service层");
        return "进入service层";
    }
}
