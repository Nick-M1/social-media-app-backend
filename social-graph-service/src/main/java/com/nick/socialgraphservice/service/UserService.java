package com.nick.socialgraphservice.service;

import com.nick.socialgraphservice.dto.UserRequest;
import com.nick.socialgraphservice.model.User;
import com.nick.socialgraphservice.model.relationships.UserFollow;
import com.nick.socialgraphservice.repository.UserFollowRepository;
import com.nick.socialgraphservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserFollowRepository userFollowRepository;

    public Flux<User> findAllUsers() {
        log.info("Get all users");
        return userRepository.findAll();
    }

    public Mono<User> findUserByUserId(String userId) {
        log.info("Get user by id = {}", userId);
        return userRepository.findUserByUserId(userId)
                .switchIfEmpty(Mono.error(new RuntimeException()));
    }
    //todo find by example (e.g. for username)

    public Mono<Long> countOfFollowing(String id) {
        log.info("Get count of following by id = {}", id);
        return userRepository.countOfFollowing(id);
    }
    public Mono<Long> countOfFollowers(String id) {
        log.info("Get count of followers by id = {}", id);
        return userRepository.countOfFollowers(id);
    }
    public Mono<Long> countOfPostsLikedByUser(String id) {
        log.info("Get count of posts liked by user by userId = {}", id);
        return userRepository.countOfPostsLikedByUser(id);
    }
    public Mono<Boolean> isFollowing(String userId1, String userId2) {
        log.info("Get boolean if user1 (with id = {}) is following user2 (with id = {})", userId1, userId2);
        return userRepository.isFollowing(userId1, userId2);
    }
    public Mono<Boolean> isLikedPost(String userId, String postId) {
        log.info("Get boolean if user (with id = {}) is has liked post (with id = {})", userId, postId);
        return userRepository.isLikedPost(userId, postId);
    }
    public Flux<User> findFollowers(String id) {
        log.info("Get followers of user with id = {}", id);
        return userRepository.findFollowers(id);
    }
    public Flux<User> findFollowing(String id) {
        log.info("Get following of user with id = {}", id);
        return userRepository.findFollowing(id);
    }

    public Mono<User> createUser(UserRequest userRequest) {
        log.info("Create user with request = {}", userRequest);
        var user = userRequest.mapToUser();
        return userRepository.save(user);
    }

//    @Transactional
//    public Mono<User> updateUserFollow(String userIdFollowing, String userIdToFollow, Boolean isFollowing) {
//        log.info("Update user-follow so that user1 (with id = {}) is following ({}) user2 (with id = {})", userIdFollowing, isFollowing, userIdToFollow);
//
//        var userFollowingMono = userRepository.findById(userIdFollowing).switchIfEmpty(Mono.error(new RuntimeException()));
//        var userToFollowMono = userRepository.findById(userIdToFollow).switchIfEmpty(Mono.error(new RuntimeException()));
//
//        return Mono.zip(userFollowingMono, userToFollowMono)
//                .filter(usersTuple -> !Objects.equals(usersTuple.getT1().getId(), usersTuple.getT2().getId()))
//                .doOnNext(usersTuple -> {
//                    if (isFollowing) {
//                        var followingSet = usersTuple.getT1().getFollowing();
//                        followingSet.add(new UserFollow(usersTuple.getT2()));
//                        usersTuple.getT1().setFollowing(followingSet);
//                    } else
//                        usersTuple.getT1().setFollowing(
//                                usersTuple.getT1().getFollowing().stream().filter(userFollow -> !Objects.equals(userFollow.getFollowedUser().getUserId(), userIdFollowing)).collect(Collectors.toSet())
//                        );
//                })
//                .doOnNext(System.out::println)
//                .map(Tuple2::getT1)
//                .flatMap(userRepository::save)
//                .flatMap(r -> userFollowRepository.saveAll(r.getFollowing()).collectList().map(h -> r));
//    }

//    @Transactional
//    public Mono<User> updateUserFollow(String userIdFollowing, String userIdToFollow, Boolean isFollowing) {
//        log.info("Update user-follow so that user1 (with id = {}) is following ({}) user2 (with id = {})", userIdFollowing, isFollowing, userIdToFollow);
//
//        var userFollowingMono = userRepository.findById(userIdFollowing).switchIfEmpty(Mono.error(new RuntimeException()));
//        var userToFollowMono = userRepository.findById(userIdToFollow).switchIfEmpty(Mono.error(new RuntimeException()));
//
//        return Mono.zip(userFollowingMono, userToFollowMono)
//                .filter(usersTuple -> !Objects.equals(usersTuple.getT1().getId(), usersTuple.getT2().getId()))
//                .doOnNext(usersTuple -> {
//                    if (isFollowing)
//                        usersTuple.getT1().addFollowing(usersTuple.getT2());
//                    else
//                        usersTuple.getT1().removeFollowing(usersTuple.getT2());
//                })
//                .doOnNext(System.out::println)
//                .map(Tuple2::getT1)
////                .flatMap(userRepository::save)
////                .flatMap(r -> userFollowRepository.saveAll(r.getFollowing()).collectList().map(h -> r))
//                ;
//    }

    @Transactional
    public Mono<User> updateUserFollow(String userIdFollowing, String userIdToFollow, Boolean isFollowing) {
        log.info("Update user-follow so that user1 (with id = {}) is following ({}) user2 (with id = {})", userIdFollowing, isFollowing, userIdToFollow);
        return userRepository.updateFollowRelationship(userIdFollowing, userIdToFollow)
                .then(userRepository.findUserByUserId(userIdFollowing));
    }
}
