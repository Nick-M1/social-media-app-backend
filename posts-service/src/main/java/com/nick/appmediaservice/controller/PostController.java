package com.nick.appmediaservice.controller;

import com.nick.appmediaservice.dto.PostRequest;
import com.nick.appmediaservice.model.Post;
import com.nick.appmediaservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping(path = "/{postId}")
    public Mono<Post> findPostById(@PathVariable String postId) {
        return postService.findPostById(postId);
    }

    @GetMapping(path = "/bulk/list/{postIds}")
    public Flux<Post> findPostsByIdsList(@PathVariable Flux<String> postIds) {
        return postService.findPostsByIdsList(postIds);
    }

    @GetMapping(path = "/bulk/map/{postIds}")
    public Mono<Map<String, Post>> findPostsByIdsMap(@PathVariable Flux<String> postIds) {
        return postService.findPostsByIdsMap(postIds);
    }

    @GetMapping(path = "/search")
    public Flux<Post> findPostsByTextSearch(@RequestParam String queryString) {
        return postService.findPostsByTextSearch(queryString);
    }

    @GetMapping(path = "/user/{userId}")
    public Flux<Post> findPostsByUserId(@PathVariable String userId, @RequestParam Optional<Integer> pageNumber) {
        return postService.findPostsByUserId(userId, pageNumber);
    }

    @GetMapping(path = "bulk/user/{userIds}")
    public Flux<Post> findPostsByUserIds(@PathVariable List<String> userIds, @RequestParam Optional<Integer> pageNumber) {
        return postService.findPostsByUserIds(userIds, pageNumber);
    }

    @GetMapping(path = "/tags")
    public Flux<Post> findPostsByTags(@RequestParam List<String> tags, @RequestParam Optional<Integer> pageNumber) {
        return postService.findPostsByTags(tags, pageNumber);
    }

    @PostMapping
    public Mono<Post> createPost(@RequestBody PostRequest postRequest) {
        return postService.createPost(postRequest);
    }
}
