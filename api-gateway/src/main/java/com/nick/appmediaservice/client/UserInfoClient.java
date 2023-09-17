package com.nick.appmediaservice.client;

import com.nick.appmediaservice.dto.UserDetailsImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

public interface UserInfoClient {
    @GetExchange("/api/user/auth")
    Mono<UserDetailsImpl> getUserByUsername(@RequestParam String username);
}
