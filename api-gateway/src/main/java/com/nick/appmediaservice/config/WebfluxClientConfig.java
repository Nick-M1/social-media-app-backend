package com.nick.appmediaservice.config;

import com.netflix.discovery.EurekaClient;
import com.nick.appmediaservice.client.UserInfoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebfluxClientConfig {
    @Bean
    public UserInfoClient userInfoClient(EurekaClient discoveryClient) {
        var clientHostname = discoveryClient.getNextServerFromEureka("USER-DETAILS-SERVICE", false).getHomePageUrl();

        var webClient = WebClient.builder()
                .baseUrl(clientHostname)
                .build();

        return HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build()
                .createClient(UserInfoClient.class);
    }
}
