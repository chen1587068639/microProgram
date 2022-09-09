package com.chen.user.service.Impl;

import com.chen.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@DubboService
public class UserServiceImpl implements UserService {
    @Override
    public String selectUser() {
        return "chen";
    }
}
