package com.nick.appmediaservice;

import com.nick.socialgraphservice.SocialGraphServiceApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestAppMediaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(SocialGraphServiceApplication::main).with(TestAppMediaServiceApplication.class).run(args);
    }

}
