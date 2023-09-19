package com.nick.appmediaservice.service;

import com.nick.appmediaservice.client.SocialGraphServiceClient;
import com.nick.appmediaservice.dto.PostRequest;
import com.nick.appmediaservice.dto.SocialGraphPostNodeRequest;
import com.nick.appmediaservice.dto.SocialGraphPostNodeResponse;
import com.nick.appmediaservice.model.Post;
import com.nick.appmediaservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final SocialGraphServiceClient socialGraphServiceClient;
    private final int PAGE_SIZE = 10;

    public Mono<Post> findPostById(String postId) {
        log.info("Get post by id = {}", postId);
        return postRepository.findById(postId);
    }

    public Mono<Map<String, Post>> findPostsByIdsMap(Flux<String> postIds) {
        log.info("Get posts by ids = {}", postIds);
        return postIds.flatMap(postId -> postRepository.findById(postId).map(res -> Map.entry(postId, res)))
                .collectMap(Map.Entry::getKey, Map.Entry::getValue);
    }
    public Flux<Post> findPostsByIdsList(Flux<String> postIds) {
        log.info("Get posts by ids = {}", postIds);
        return postIds.flatMap(postRepository::findById);
    }

    public Flux<Post> findPostsByTextSearch(String queryString) {
        log.info("Get posts by text-search = {}", queryString);
        return postRepository.findPostsByTextSearch(queryString);
    }

    public Flux<Post> findPostsByUserId(String userId, Optional<Integer> pageNumber) {
        log.info("Get posts by user-id = {}", userId);
        var pageable = PageRequest.of(pageNumber.orElse(0), PAGE_SIZE, Sort.by(Sort.Direction.ASC, "createdAt"));
        return postRepository.findPostsByUserId(userId, pageable);
    }

    public Flux<Post> findPostsByUserIds(List<String> userIds, Optional<Integer> pageNumber) {
        log.info("Get posts by user-ids = {}", userIds);
        var pageable = PageRequest.of(pageNumber.orElse(0), PAGE_SIZE, Sort.Direction.ASC);
        return postRepository.findPostsByUserIdInOrderByCreatedAt(userIds, pageable);
    }

    public Flux<Post> findPostsByTags(List<String> tags, Optional<Integer> pageNumber) {
        log.info("Get posts by tags = {}", tags);
        var pageable = PageRequest.of(pageNumber.orElse(0), PAGE_SIZE, Sort.Direction.ASC);
        return postRepository.findPostsByTagsContainsIgnoreCaseOrderByCreatedAt(tags, pageable);
    }

    public Mono<Post> createPost(PostRequest postRequest) {
        log.info("Create post with request = {}", postRequest);
        var socialGraphPostNodeRequest = new SocialGraphPostNodeRequest(postRequest.userId());
        return socialGraphServiceClient.createPost(socialGraphPostNodeRequest)
                .map(SocialGraphPostNodeResponse::id)
                .map(postRequest::mapToPost)
                .flatMap(postRepository::save);
    }
}
