package com.nick.socialgraphservice.controller;

import com.nick.socialgraphservice.dto.UserRequest;
import com.nick.socialgraphservice.model.User;
import com.nick.socialgraphservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
// todo - GRAPHQL
@RestController
@RequestMapping(path = "/api/social/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(path = "{id}")
    public Mono<User> findUserById(@PathVariable String id) {
        return userService.findUserByUserId(id);
    }

    @GetMapping(path = "count/following")
    public Mono<Long> countOfFollowing(@RequestParam String id) {
        return userService.countOfFollowing(id);
    }
    @GetMapping(path = "count/followers")
    public Mono<Long> countOfFollowers(@RequestParam String id) {
        return userService.countOfFollowers(id);
    }
    @GetMapping(path = "count/liked")
    public Mono<Long> countOfPostsLikedByUser(@RequestParam String id) {
        return userService.countOfPostsLikedByUser(id);
    }
    @GetMapping(path = "boolean/following")
    public Mono<Boolean> isFollowing(@RequestParam String userId1, @RequestParam String userId2) {
        return userService.isFollowing(userId1, userId2);
    }
    @GetMapping(path = "boolean/liked")
    public Mono<Boolean> isLikedPost(@RequestParam String userId, @RequestParam String postId) {
        return userService.isLikedPost(userId, postId);
    }
    @GetMapping(path = "followers")
    public Flux<User> findFollowers(@RequestParam String id) {
        return userService.findFollowers(id);
    }
    @GetMapping(path = "following")
    public Flux<User> findFollowing(@RequestParam String id) {
        return userService.findFollowing(id);
    }

    @PostMapping
    public Mono<User> createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}
