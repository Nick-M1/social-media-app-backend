package com.nick.socialgraphservice.controller;

import com.nick.socialgraphservice.dto.PostRequest;
import com.nick.socialgraphservice.model.Post;
import com.nick.socialgraphservice.model.User;
import com.nick.socialgraphservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/social/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

//    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public Flux<User> findAllUsers() {
//        return postService.find.findAllUsers();
//    }

    @GetMapping(path = "{postId}")
    public Mono<Post> findPostById(@PathVariable String postId) {
        return postService.findPostById(postId);
    }

    @GetMapping(path = "/following/{userId}")
    public Flux<Post> findPostsByUserFollowing(@PathVariable String userId) {
        return postService.findPostsByUserFollowing(userId);
    }

    @PostMapping
    public Mono<Post> createPost(@RequestBody PostRequest postRequest) {
        return postService.createPost(postRequest);
    }

    @PutMapping("/like")
    public Mono<User> updatePostLike(@RequestParam String userId, @RequestParam String postId, @RequestParam(defaultValue = "true") Boolean isLiked) {
        return postService.updatePostLike(userId, postId, isLiked);
    }
}
