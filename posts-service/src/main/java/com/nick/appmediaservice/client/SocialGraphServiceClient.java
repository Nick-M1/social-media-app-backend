package com.nick.appmediaservice.client;

import com.nick.appmediaservice.dto.SocialGraphPostNodeRequest;
import com.nick.appmediaservice.dto.SocialGraphPostNodeResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface SocialGraphServiceClient {
    @PostExchange(url = "/api/social/post")
    Mono<SocialGraphPostNodeResponse> createPost(@RequestBody SocialGraphPostNodeRequest socialGraphPostNodeRequest);
}
