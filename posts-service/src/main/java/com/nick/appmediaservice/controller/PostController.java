package com.nick.appmediaservice.controller;

import com.nick.appmediaservice.model.Post;
import com.nick.appmediaservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping(path = "/{postIds}")
    Mono<Post> findPostById(@PathVariable String postId) {
        return postService.findPostById(postId);
    }

    @GetMapping(path = "/bulk/{postIds}")
    Flux<Post> findPostsByIds(@PathVariable Flux<String> postIds) {
        return postService.findPostsByIdsList(postIds);
    }
}
