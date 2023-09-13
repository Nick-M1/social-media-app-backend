package com.nick.appmediaservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestFeedServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(ApiGatewayApplication::main).with(TestFeedServiceApplication.class).run(args);
    }

}
