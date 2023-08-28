package com.nick.appmediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestAppMediaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(UserDetailsServiceApplication::main).with(TestAppMediaServiceApplication.class).run(args);
    }

}
