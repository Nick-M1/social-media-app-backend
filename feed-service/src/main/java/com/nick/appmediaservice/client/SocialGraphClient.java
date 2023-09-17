package com.nick.appmediaservice.client;

import com.nick.appmediaservice.dto.PostNode;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;

public interface SocialGraphClient {
    @GetExchange("/api/social/post/following/{userId}")
    Flux<PostNode> findPostsByUserFollowing(@PathVariable String userId);
}
