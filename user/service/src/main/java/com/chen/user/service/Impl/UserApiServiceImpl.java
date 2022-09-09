package com.chen.user.service.Impl;

import com.chen.api.UserApi;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userApi")
@DubboService
public class UserApiServiceImpl implements UserApi {
    @Override
    public String selectUser() {
        return "I'm RPC";
    }
}
