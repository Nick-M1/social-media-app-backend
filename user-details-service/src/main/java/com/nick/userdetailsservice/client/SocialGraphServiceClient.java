package com.nick.userdetailsservice.client;

import com.nick.userdetailsservice.dto.SocialGraphUserNodeRequest;
import com.nick.userdetailsservice.dto.SocialGraphUserNodeResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface SocialGraphServiceClient {
    @PostExchange(url = "/api/social/user")
    SocialGraphUserNodeResponse createUser(@RequestBody SocialGraphUserNodeRequest socialGraphUserNodeRequest);
}
