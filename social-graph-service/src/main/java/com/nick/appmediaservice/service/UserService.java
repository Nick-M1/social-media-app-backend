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

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Flux<User> findAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> findUserById(String id) {
        return userRepository.findById(id).switchIfEmpty(Mono.error(new RuntimeException()));
    }
    //todo find by example (e.g. for username)

    public Mono<Long> countOfFollowing(String id) {
        return userRepository.countOfFollowing(id);
    }
    public Mono<Long> countOfFollowers(String id) {
        return userRepository.countOfFollowers(id);
    }
    public Mono<Long> countOfPostsLikedByUser(String id) {
        return userRepository.countOfPostsLikedByUser(id);
    }
    public Mono<Boolean> isFollowing(String userId1, String userId2) {
        return userRepository.isFollowing(userId1, userId2);
    }
    public Mono<Boolean> isLikedPost(String userId, String postId) {
        return userRepository.isLikedPost(userId, postId);
    }
    public Flux<User> findFollowers(String id) {
        return userRepository.findFollowers(id);
    }
    public Flux<User> findFollowing(String id) {
        return userRepository.findFollowing(id);
    }

    public Mono<User> createUser(UserRequest userRequest) {
        var user = new User(userRequest.id());
        return userRepository.save(user);
    }

    @Transactional
    public Mono<User> updateUserFollow(String userIdFollowing, String userIdToFollow, Boolean isFollowing) {
        var userFollowingMono = userRepository.findById(userIdFollowing).switchIfEmpty(Mono.error(new RuntimeException()));
        var userToFollowMono = userRepository.findById(userIdToFollow).switchIfEmpty(Mono.error(new RuntimeException()));

        return Mono.zip(userFollowingMono, userToFollowMono)
                .filter(usersTuple -> !Objects.equals(usersTuple.getT1().getId(), usersTuple.getT2().getId()))
                .doOnNext(usersTuple -> {
                    if (isFollowing)
                        usersTuple.getT1().getFollowing().add(new UserFollow(usersTuple.getT2()));
                    else
                        usersTuple.getT1().setFollowing(
                                usersTuple.getT1().getFollowing().stream().filter(userFollow -> !Objects.equals(userFollow.getFollowedUser().getUserId(), userIdFollowing)).collect(Collectors.toSet())
                        );
                })
                .map(Tuple2::getT1);
    }
}
