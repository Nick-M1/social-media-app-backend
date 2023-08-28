package com.nick.appmediaservice.controller;

import com.nick.appmediaservice.dto.UserRequest;
import com.nick.appmediaservice.model.User;
import com.nick.appmediaservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/api/v1/media/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(path = "{id}")
    public Mono<User> findUserById(@PathVariable String id) {
        return userService.findUserById(id);
    }

    @PutMapping
    public Mono<User> createUser(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }
}
