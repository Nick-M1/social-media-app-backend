package com.nick.appmediaservice.config;

import com.nick.appmediaservice.client.PostDetailsClient;
import com.nick.appmediaservice.client.SocialGraphClient;
import com.nick.appmediaservice.client.UserDetailsClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

// todo: https://www.baeldung.com/spring-6-http-interface
@Configuration
public class HttpInterfaceConfig {

    @Bean
    public UserDetailsClient userDetailsClient() {
        var webClient = WebClient.builder()
                .baseUrl("...")
                .build();

        return HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build()
                .createClient(UserDetailsClient.class);
    }

    @Bean
    public SocialGraphClient socialGraphClient() {
        var webClient = WebClient.builder()
                .baseUrl("...")
                .build();

        return HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build()
                .createClient(SocialGraphClient.class);
    }

    @Bean
    public PostDetailsClient postDetailsClient() {
        var webClient = WebClient.builder()
                .baseUrl("...")
                .build();

        return HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build()
                .createClient(PostDetailsClient.class);
    }
}
