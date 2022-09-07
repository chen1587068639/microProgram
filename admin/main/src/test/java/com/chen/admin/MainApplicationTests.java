package com.chen.admin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MainApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("hello world");
        for (int i=0;i<100;i++){
            System.out.println("项目启动中");
        }
    }

}
