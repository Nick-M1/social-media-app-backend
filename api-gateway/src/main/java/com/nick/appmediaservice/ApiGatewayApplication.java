package com.nick.appmediaservice;

import com.nick.appmediaservice.properties.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

// todo https://spring.io/guides/gs/gateway/
@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
