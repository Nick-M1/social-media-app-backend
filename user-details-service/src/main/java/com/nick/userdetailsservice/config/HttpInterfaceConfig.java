package com.nick.userdetailsservice.config;

import com.netflix.discovery.EurekaClient;
import com.nick.userdetailsservice.client.SocialGraphServiceClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class HttpInterfaceConfig {

    @Bean
    public SocialGraphServiceClient socialGraphServiceClient(EurekaClient discoveryClient) {
        var clientHostname = discoveryClient.getNextServerFromEureka("SOCIAL-GRAPH-SERVICE", false).getHomePageUrl();

        var webClient = WebClient.builder()
                .baseUrl(clientHostname)
                .build();

        return HttpServiceProxyFactory
                .builderFor(WebClientAdapter.create(webClient))
                .build()
                .createClient(SocialGraphServiceClient.class);
    }
}
