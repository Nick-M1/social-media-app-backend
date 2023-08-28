package com.nick.appmediaservice.service;

import com.nick.appmediaservice.dto.UserRequest;
import com.nick.appmediaservice.model.User;
import com.nick.appmediaservice.model.relationships.UserFollow;
import com.nick.appmediaservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.HashSet;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> findUserById(String id) {
        return userRepository.findById(id);
    }
    //todo find by example (e.g. for username)

    public Mono<User> createUser(UserRequest userRequest) {
        var user = new User(userRequest.username(), userRequest.firstName(), userRequest.lastName(), userRequest.profilePicture(), new HashSet<>());
        return userRepository.save(user);
    }

    @Transactional
    public Mono<User> followUser(String userIdFollowing, String userIdToFollow) {
        var userFollowingMono = userRepository.findById(userIdFollowing).switchIfEmpty(Mono.error(new RuntimeException()));
        var userToFollowMono = userRepository.findById(userIdToFollow).switchIfEmpty(Mono.error(new RuntimeException()));

        return Mono.zip(userFollowingMono, userToFollowMono)
                .filter(usersTuple -> !Objects.equals(usersTuple.getT1().getId(), usersTuple.getT2().getId()))
                .doOnNext(usersTuple -> usersTuple.getT1().getFollowing().add(new UserFollow(usersTuple.getT2())))
                .map(Tuple2::getT1);
    }
}
