package com.nick.appmediaservice.service;

import com.nick.appmediaservice.model.Post;
import com.nick.appmediaservice.model.User;
import com.nick.appmediaservice.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;

    public Mono<Post> findPostById(String postId) {
        return postRepository.findById(postId)
                .switchIfEmpty(Mono.error(new RuntimeException()));
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

    public Mono<Post> createPost(String postId, String userCreatedById) {
        return userService.findUserById(userCreatedById)
                .map(u -> new Post(postId, u))
                .flatMap(postRepository::save);
    }


    @Transactional
    public Mono<User> updatePostLike(String userId, String postId, Boolean isLiked) {
        var user = userService.findUserById(userId);
        var post = this.findPostById(postId);

        return Mono.zip(user, post)
                .doOnNext(tuple -> {
                    if (isLiked)
                        tuple.getT1().getLikedPosts().add(tuple.getT2());
                    else
                        tuple.getT1().getLikedPosts().remove(tuple.getT2());
                })
                .map(Tuple2::getT1);
    }
}
