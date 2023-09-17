package com.nick.appmediaservice.client;

import com.nick.appmediaservice.dto.Post;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public interface PostDetailsClient {
    @GetExchange("/api/post/bulk/{postIds}")
    Flux<Post> findPostsByIdsList(@PathVariable List<String> postIds);
}
