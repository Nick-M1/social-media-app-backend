package com.nick.socialgraphservice.service;

import com.nick.socialgraphservice.dto.PostRequest;
import com.nick.socialgraphservice.model.Post;
import com.nick.socialgraphservice.model.User;
import com.nick.socialgraphservice.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public Mono<Post> findPostById(String postId) {
        log.info("Get post by id = {}", postId);
        return postRepository.findById(postId)
                .switchIfEmpty(Mono.error(new RuntimeException("User with id " + postId + " does not exist")));
    }

    public Mono<Long> countOfUserLikesOnPost(String postId) {
        return postRepository.countOfUserLikesOnPost(postId);
    }
    public Flux<Post> findPostsLikedByUser(String userId) {
        return postRepository.findPostsLikedByUser(userId);
    }
    public Mono<Post> findPostByComment(String commentId) {
        return postRepository.findPostByComment(commentId);
    }
    public Flux<Post> findPostsByUser(String userId) {
        return postRepository.findPostsByUser(userId);
    }

    public Flux<Post> findPostsByUserFollowing(String userId) {
        log.info("Get posts by users that user (with userId = {}) is following", userId);
        return userService.findFollowing(userId)
                .map(User::getId)
                .flatMap(this::findPostsByUser);
    }

    public Mono<Post> createPost(PostRequest postRequest) {
        log.info("Create post with request = {}", postRequest);
        return userService.findUserByUserId(postRequest.userId())
                .map(Post::new)
                .flatMap(postRepository::save);
    }


//    @Transactional
//    public Mono<User> updatePostLike(String userId, String postId, Boolean isLiked) {
//        log.info("Update post-likes - user with userId={} likes post with postId={} is {}", userId, postId, isLiked);
//        var user = userService.findUserByUserId(userId);
//        var post = this.findPostById(postId);
//
//        return Mono.zip(user, post)
//                .doOnNext(tuple -> {
//                    if (isLiked)
//                        tuple.getT1().getLikedPosts().add(tuple.getT2());
//                    else
//                        tuple.getT1().getLikedPosts().remove(tuple.getT2());
//                })
//                .map(Tuple2::getT1);
//    }

    @Transactional
    public Mono<User> updatePostLike(String userId, String postId, Boolean isLiked) {
        log.info("Update post-likes - user with userId={} likes post with postId={} is {}", userId, postId, isLiked);
        var user = userService.findUserByUserId(userId);
        var post = this.findPostById(postId);

        return Mono.zip(user, post)
                .doOnNext(tuple -> {
                    if (isLiked)
                        tuple.getT1().addLikedPost(tuple.getT2());
                    else
                        tuple.getT1().removeLikedPost(tuple.getT2());
                })
                .map(Tuple2::getT1);
    }
}
