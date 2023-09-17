package com.nick.appmediaservice.client;

import com.nick.appmediaservice.dto.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserDetailsClient {
    @GetExchange("/api/user/info/{userIds}")
    Mono<Map<String, UserDetails>> getUsersByIds(@PathVariable List<String> userIds);
}
