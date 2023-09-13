package com.nick.appmediaservice.service;

import com.nick.appmediaservice.model.Post;
import com.nick.appmediaservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final int PAGE_SIZE = 10;

    public Mono<Post> findPostById(String postId) {
        return postRepository.findById(postId);
    }

    public Flux<Post> findPostsByTextSearch(String queryString) {
        return postRepository.findPostByTextSearch(queryString);
    }

    public Flux<Post> findPostsByUserId(String userId, Optional<Integer> pageNumber) {
        var pageable = PageRequest.of(pageNumber.orElse(0), PAGE_SIZE, Sort.Direction.ASC);
        return postRepository.findPostsByUserIdOrderByCreatedAt(userId, pageable);
    }

    public Flux<Post> findPostsByUserIds(List<String> userIds, Optional<Integer> pageNumber) {
        var pageable = PageRequest.of(pageNumber.orElse(0), PAGE_SIZE, Sort.Direction.ASC);
        return postRepository.findPostsByUserIdInOrderByCreatedAt(userIds, pageable);
    }

    public Flux<Post> findPostsByTags(List<String> tags, Optional<Integer> pageNumber) {
        var pageable = PageRequest.of(pageNumber.orElse(0), PAGE_SIZE, Sort.Direction.ASC);
        return postRepository.findPostsByTagsContainsIgnoreCaseOrderByCreatedAt(tags, pageable);
    }

    public Mono<Post> createPost(Post post) {
        return postRepository.save(post);
    }
}
